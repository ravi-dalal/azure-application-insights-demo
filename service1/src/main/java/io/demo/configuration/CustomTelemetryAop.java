package io.demo.configuration;

import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.RemoteDependencyTelemetry;
import com.microsoft.applicationinsights.telemetry.RequestTelemetry;
import com.microsoft.applicationinsights.web.internal.ThreadContext;

@Aspect
@Configuration
public class CustomTelemetryAop {
	
	Logger logger = LoggerFactory.getLogger(CustomTelemetryAop.class);

	@Around("@annotation(io.demo.configuration.CustomTelemetry)")
	@Order(1)
	public Object CustomTelemetry(ProceedingJoinPoint joinPoint) throws Throwable {

		Object result;
		Instant startTime = Instant.now();
		RemoteDependencyTelemetry dependencyTelemetry = new RemoteDependencyTelemetry();
		RequestTelemetry requestTelemetry = ThreadContext.getRequestTelemetryContext().getHttpRequestTelemetry();

		try {

			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();
			CustomTelemetry customTelemetry = method.getAnnotation(CustomTelemetry.class);
			logger.debug("Azure Application Insights Request Id={}", requestTelemetry.getId());
			startTime = Instant.now();
			dependencyTelemetry.setId(requestTelemetry.getId());
			dependencyTelemetry.setName(method.getName());
			dependencyTelemetry.setType(customTelemetry.type());
			result = joinPoint.proceed();
		} catch (Exception ex) {
			logger.error("Error occurred", ex);
			throw ex;

		} finally {
			Instant endTime = Instant.now();
			Duration delta = Duration.between(startTime, endTime);
			dependencyTelemetry.setDuration(new com.microsoft.applicationinsights.telemetry.Duration(delta.toMillis()));
			TelemetryClient telemetryClient = new TelemetryClient();
			telemetryClient.trackDependency(dependencyTelemetry);
		}

		return result;
	}

}
