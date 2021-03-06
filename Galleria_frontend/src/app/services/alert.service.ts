import { Injectable } from '@angular/core';
import { Router, NavigationStart } from "@angular/router";
import { Observable, Subject } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  private subject = new Subject<any>();
  private keepRouterOnChange = false;

  constructor( private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationStart) {
        if (this.keepRouterOnChange) {
            this.keepRouterOnChange = false;
        } else {
            this.clear();
        }
      }
    });
   }

   getAlert(): Observable<any> {
     return this.subject.asObservable();
   }

   success(message: string, keepRouterOnChange = false) {
     this.keepRouterOnChange = keepRouterOnChange;
     this.subject.next({type: 'success', text: message});
   }

   error( message: string, keepRouterOnChange = false) {
    this.keepRouterOnChange = keepRouterOnChange;
    this.subject.next({type: 'error', text: message});
   }

   clear( ) {
     // calling next without parameters should clear<?>
     this.subject.next();
   }

}
