import { Component, OnInit } from '@angular/core';
import { AddressCrudService } from '../../shared/service/address-crud.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-address-update',
  templateUrl: './address-update.component.html',
  styleUrls: ['./address-update.component.css']
})
export class AddressUpdateComponent implements OnInit {
  patientId = this.actRoute.snapshot.params['id1'];
  addressId = this.actRoute.snapshot.params['id2'];
  address: any = {};

  ufs = ['AC','AL','AM','AP','BA','CE','DF','ES','GO','MA','MG','MS','MT','PA','PB','PE','PI','PR',
         'RJ','RN','RO','RR','RS','SC','SE','SP','TO'];
  constructor(
    public restApi: AddressCrudService,
    public actRoute: ActivatedRoute,
    public router: Router
  ) { }

  ngOnInit() {
    this.restApi.getAddress(this.patientId, this.addressId).subscribe((data: {}) => {
      this.address = data;
    })
  }
  updateAddress(){
    if(window.confirm('Você tem certeza que deseja alterar este Endereço?')){
      this.restApi.updateAddress(this.patientId, this.addressId, this.address).subscribe(data => {
        this.router.navigate(['/paciente-detalhes/' + this.patientId])
      })
    }
  }

}
