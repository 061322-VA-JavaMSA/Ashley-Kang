let apiUrl = 'http://localhost:8080/project1';

let principalString = sessionStorage.getItem('principal');
let principal = null;

let navigationBarUL = document.getElementById('navBar');
let button = document.createElement('button');
button.addEventListener('click', logout);

if(principalString){
    principal = JSON.parse(principalString);
    navBar();
}
else{
    nullNavBar();
}

function navBar(){
    button.id = 'Logout';
    let li = document.createElement('li');
    li.appendChild(button);
    navigationBarUL.appendChild(li);

    li = document.createElement('li');
    let a = document.createElement('a');
    li = document.createElement('li');
    a = document.createElement('a');
    a.innerHTML = 'Home';
    a.setAttribute('href', '../html/homepage.html');
    li.appendChild(a);
    navigationBarUL.appendChild(li);


    li = document.createElement('li');
    a = document.createElement('a');
        a.innerHTML='Account';
        a.setAttribute('href', '../html/account.html');
        li.appendChild(a);
        navigationBarUL.appendChild(li);

    if(principal.role === 'MANAGER'){
        li = document.createElement('li');
        a = document.createElement('a');
        a.innerHTML = 'View Requests';
        a.setAttribute('href', '../html/viewreqM.html');
        li.appendChild(a);
        navigationBarUL.appendChild(li);

        li = document.createElement('li');
        a = document.createElement('a');
        a.innerHTML='View Employees';
        a.setAttribute('href', '../html/viewEmp.html');
        li.appendChild(a);
        navigationBarUL.appendChild(li);
    }
    if(principal.role === 'EMPLOYEE'){
        li = document.createElement('li');
        a = document.createElement('a');
        a.innerHTML = 'View Requests';
        a.setAttribute('href', '../html/viewreq.html');
        li.appendChild(a);
        navigationBarUL.appendChild(li);

        li = document.createElement('li');
        a = document.createElement('a');
        a.innerHTML='Submit a Request';
        a.setAttribute('href', '../html/submitreq.html');
        li.appendChild(a);
        navigationBarUL.appendChild(li);
    }

}

function nullNavBar(){
    let li = document.createElement('li');
    let a = document.createElement('a');
    a.innerHTML = 'Login';
    a.setAttribute('href', '../html/login.html');
    li.appendChild(a);
    navigationBarUL.appendChild(li);

    li = document.createElement('li');
    a = document.createElement('a');
    a.innerHTML = 'Home';
    a.setAttribute('href', '../html/homepage.html');
    li.appendChild(a);
    navigationBarUL.appendChild(li);
}

async function logout() {

    // Sends a DELETE request to API to invalidate session
    let response = await fetch(`${apiUrl}/auth`, {
        method: 'DELETE',
    });

    if (response.status == 200) {
        // clears user object JSON string in session storage
        sessionStorage.clear();
        // clears principal variable representing logged in user
        principal = null;
        window.location.href="../html/login.html";
    } else {
        console.log('Unable to logout.')
    }
}

