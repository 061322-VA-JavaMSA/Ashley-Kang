// document.getElementById('getData').onclick = getData;
document.getElementById('getData').addEventListener("click", getData);

var refreshBut = document.getElementById('refresh');
refreshBut.onclick = refresh;


let baseApiURL = 'https://pokeapi.co/api/v2/pokemon';

async function getData() {
    console.log('Button was clicked!');
    let id = document.getElementById('dataInput').value;
    console.log(`id = ${id}`);

    let httpResponse = await fetch(`${baseApiURL}/${id}`);

    if(httpResponse.status >= 200 && httpResponse.status < 300){
    let data = await httpResponse.json();

    populateData(data);
    showNameImage(data);
    createTable(data);
    
    } else {
        console.log('Invalid request.');
    }
}

function populateData(response) {
    console.log(response);
}

var nameDiv = document.getElementById('nameDiv');
var imageDiv = document.getElementById('imageDiv');
var tableDiv = document.getElementById('tableDiv');

function showNameImage(response){
    var heading1 = document.createElement('h3');
    heading1.innerHTML = response.forms[0].name;
    nameDiv.appendChild(heading1);

    var image = document.createElement('img');
    image.setAttribute('src',response.sprites.front_default);
    imageDiv.appendChild(image);

}

function createTable(response) {

    var table = document.createElement('table');
    
    var tr = document.createElement('tr');

    var td = document.createElement('th');
    td.innerHTML = 'ID No.';
    tr.appendChild(td);

    var td = document.createElement('th');
    td.innerHTML = 'Pokemon Type';
    tr.appendChild(td);

    td = document.createElement('th');
    td.innerHTML = 'Height';
    tr.appendChild(td);

    td = document.createElement('th');
    td.innerHTML = 'Weight';
    tr.appendChild(td);

    table.appendChild(tr);

    tr = document.createElement('tr');

    td = document.createElement('td');
    td.innerHTML = response.id;
    tr.appendChild(td);

    td = document.createElement('td');
    td.innerHTML = response.types[0].type.name;
    tr.appendChild(td);

    td = document.createElement('td');
    td.innerHTML = response.height;
    tr.appendChild(td);

    td = document.createElement('td');
    td.innerHTML = response.weight;
    tr.appendChild(td);

    table.appendChild(tr);

    tableDiv.appendChild(table);
}

function refresh(){
    tableDiv.innerHTML = '';
    nameDiv.innerHTML = '';
    imageDiv.removeChild(imageDiv.firstChild);
}