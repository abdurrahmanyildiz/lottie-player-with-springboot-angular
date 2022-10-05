import { Component, NgZone } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import { AnimationItem } from 'lottie-web';
import { AnimationOptions } from 'ngx-lottie';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'lottieplayer';

  isFileVisible=false;
  lFiles: any;
  options: AnimationOptions;
  private animationItem: AnimationItem;

  constructor(private http: HttpClient, private ngZone: NgZone){
    this.loadFiles();
  }

  loadFiles(){
    this.http.get(`${this.getUrl()}/lottie/all`).subscribe(res=> {
      this.lFiles = res;
    });
  }
  
  openFile(id) {

    this.isFileVisible=true;
    this.options = { path: '/api/lottie/' + id };
      
  }

  animationCreated(animationItem: AnimationItem): void {

    this.animationItem = animationItem;
  }

  stop(): void {
    this.ngZone.runOutsideAngular(() => this.animationItem.stop());
  }

  play(): void {
    this.ngZone.runOutsideAngular(() => this.animationItem.play());
  }

  getUrl(): string {
    return window.location.protocol + "//"+ window.location.hostname + (window.location.port != "" ? (":" + window.location.port) : "") + "/api";
  }

}
