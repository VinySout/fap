import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserCrudService } from '../../../shared/service/user-crud.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  @Input() user = {
    name: '',
    username: '',
    email: '',
    password: '',
    confirmPassword : ''
  }
  
  sucesso = '';
  error = '';
  erroSenha = '';
  

 constructor(public restApi: UserCrudService, public actRoute: ActivatedRoute, public router: Router) { }

  ngOnInit() {
    this.error = '';
    this.erroSenha = '';
    this.sucesso = '';
  }

  addUser(){
    this.restApi.createUser(this.user).subscribe(
      (data: {}) => {
        this.error = '';
        this.erroSenha = '';
        this.sucesso = "Cadastro Realizado com Sucesso!";
      },
    error => {
      this.sucesso = '';
      this.error = error;
    })
  }
  validaSenha(){
    if(this.user.password != this.user.confirmPassword){
      this.error = '';
      this.erroSenha = 'Os campos Senha e Confirmar Senha não conferem';
    } else{
      this.erroSenha = '';
      this.addUser();
    }
  }
}