import { Appointment } from './appointment';
import { Exams } from './exams';
import { Endereco } from './endereco';

export class Paciente {
    idPaciente: string;
    cpf: string;
    identidade: string;
    idade: string;
    telefone: string;
    nomePaciente: string;
    nomeMae: string;
    estadoCivil: string;
    dataNasc: string;
    dum: string;
    dpp: string;
    altura: string;
    peso: string;
    imc: string;
    consultas: Appointment;
    exames: Exams;
    endereco: Endereco;
    createdAt: string;
    updatedAt: string;
}
