import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PhoneMainComponent } from './phone-main/phone-main.component';

export const routes: Routes = [

  {
    path: '',
   component: PhoneMainComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }