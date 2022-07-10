let apiUrl = 'http://localhost:8080/project1';

let principalString = sessionStorage.getItem('principal');
let principal = null;

if(principalString){
    principal = JSON.parse(principalString);
}
