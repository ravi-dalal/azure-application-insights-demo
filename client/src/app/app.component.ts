import { Component } from '@angular/core';
import { AzureAppInsights } from 'src/services/AzureAppInsights';
import { ApiService } from 'src/services/ApiService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'az-appinsights-demo';
  returnedMessage: string;

  constructor(private appInsights: AzureAppInsights, private apiService: ApiService) {}

  async ngOnInit() {
    this.apiService.sendGetRequest().subscribe((data: any)=>{
      console.log("Data=", data);
      this.returnedMessage = data;
    })
}
}
