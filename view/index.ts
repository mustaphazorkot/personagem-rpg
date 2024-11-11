const url ="http://localhost:8080/personagem/usuario/1";

function hideLoader() {
    const loading = document.getElementById("loading");
    if (loading) {
        loading.style.display = "none";
    }
}

function show(personagens: any) {

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

    for(let personagem of personagens){
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

    const personagensElement  = document.getElementById("personagens");
    if(personagensElement ){
        personagensElement .innerHTML = table;
    }

}

async function getAPI(url: RequestInfo | URL) {
    const response = await fetch(url, {method: "GET"})

    var data = await response.json();
    console.log(data);
    if(response){
        hideLoader();
    }
    show(data)
}

getAPI(url);
