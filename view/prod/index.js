"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
const url = "http://localhost:8080/personagem/usuario/1";
function hideLoader() {
    const loading = document.getElementById("loading");
    if (loading) {
        loading.style.display = "none";
    }
}
function show(personagens) {
    let table = `
        <thead>
        <th scope="col">#</th>
        <th scope="col">Nome</th>
        <th scope="col">Raca</th>
        <th scope="col">Classe</th>
        <th scope="col">Forca</th>
        <th scope="col">Destreza</th>
        <th scope="col">Constituicao</th>
        <th scope="col">Inteligencia</th>
        <th scope="col">Sabedoria</th>
        <th scope="col">Carisma</th>
        </thead>`;
    for (let personagem of personagens) {
        table += `
            <tr>
                <td scope="row"> ${personagem.id}</td>
                <td> ${personagem.nome}</td>
                <td> ${personagem.raca.nome}</td>
                <td> ${personagem.classe.nome}</td>
                <td> ${personagem.forca}</td>
                <td> ${personagem.destreza}</td>
                <td> ${personagem.constituicao}</td>
                <td> ${personagem.inteligencia}</td>
                <td> ${personagem.sabedoria}</td>
                <td> ${personagem.carisma}</td>
            </tr>`;
    }
    const personagensElement = document.getElementById("personagens");
    if (personagensElement) {
        personagensElement.innerHTML = table;
    }
}
function getAPI(url) {
    return __awaiter(this, void 0, void 0, function* () {
        const response = yield fetch(url, { method: "GET" });
        var data = yield response.json();
        console.log(data);
        if (response) {
            hideLoader();
        }
        show(data);
    });
}
getAPI(url);
