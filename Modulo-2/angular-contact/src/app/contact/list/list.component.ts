import { Component, OnInit } from '@angular/core';
import { Contact } from '../contact';
import { ContactService } from '../contact.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  contacts: Contact[];

  constructor(private service: ContactService) { }

  ngOnInit() {
    this.getAll();
  }
  getAll() {
    this.service.getAll().subscribe(contact => {
      this.contacts = <Contact[]> contact['data'];
    });
  }

}
