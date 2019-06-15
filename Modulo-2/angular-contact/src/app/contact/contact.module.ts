import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListComponent } from './list/list.component';
import { FormComponent } from './form/form.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [ListComponent, FormComponent],
  imports: [
    CommonModule,
    HttpClientModule
  ],
  exports: [ListComponent, FormComponent]
})
export class ContactModule { }
