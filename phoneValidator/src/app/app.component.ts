import { Component, OnInit, ViewChild } from '@angular/core';
import { PhoneService } from './services/phone.service';
import { PhoneModel } from './_models/phone';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'phoneValidator';
  phoneTable: PhoneModel[] = [];
  displayedColumns: string[] = ["name", "phone", "country", "countryCode", "state"];
  totalResults: number;
  defaultRecords: number = 5;
  out: any[];
  pageEvent: any;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  size: number;
  pageId: number;
  country: string = "";
  countryCode: string;
  state: boolean;
  constructor(public phoneService: PhoneService) { }

  ngOnInit(){
    this.phoneService.getCustomers(this.country, this.state).subscribe(res => {
      this.phoneTable = res.slice(0, 5);
      this.totalResults = res.length;
      this.out = res;
    });
  };
  handlePageEvent(event: PageEvent) {
    this.size = event.pageSize;
    this.pageId = event.pageIndex;
  }

  onPaginatedChange(data: any){
    this.phoneTable = this.out.slice(data.pageIndex * data.pageSize, data.pageIndex * data.pageSize + data.pageSize);
  }
}
