import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AddressCrudService } from '../../shared/service/address-crud.service';

@Component({
  selector: 'app-address-create',
  templateUrl: './address-create.component.html',
  styleUrls: ['./address-create.component.css']
})
export class AddressCreateComponent implements OnInit {

  idPaciente = this.actRoute.snapshot.params['id'];
  @Input() address = {
    cep: '',
    uf: '',
    cidade: '',
    bairro: '',
    rua: '',
    numero: ''
}

ufs = ['AC','AL','AM','AP','BA','CE','DF','ES','GO','MA','MG','MS','MT','PA','PB','PE','PI','PR',
       'RJ','RN','RO','RR','RS','SC','SE','SP','TO'];

  constructor(
    public restApi: AddressCrudService,
    public actRoute: ActivatedRoute,
    public router: Router
  ) { }

  ngOnInit() {
  }
  addAddress(dataAddress){
    this.restApi.createAddress(this.idPaciente, this.address).subscribe((data: {}) => {
      this.router.navigate(['/paciente-detalhes/' + this.idPaciente])
    })
  }

}
