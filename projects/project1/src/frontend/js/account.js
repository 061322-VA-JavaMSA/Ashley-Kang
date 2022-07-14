let update = document.getElementById('update');
update.addEventListener('click', updateAccount);


viewDetails();

async function viewDetails(){
    let response = await fetch(`${apiUrl}/account/${principal.id}`, {
        method: 'GET',
        headers: {
           'Content-Type': 'application/x-www-form-urlencoded'
        }
    });
    if(response.status = 200){
        let data = await response.json();
        //display details

        let nameBox = document.getElementById('name');
        nameBox.placeholder = data.user_name;

        let userBox = document.getElementById('username');
        userBox.placeholder = data.username;

        let passBox = document.getElementById('password');
        passBox.placeholder = data.user_pass;

    } else{
        console.log('Unable to login.')
    }
}

async function updateAccount(){
    let newName = document.getElementById('name').value;
    let newUsername = document.getElementById('username').value;
    let newPassword = document.getElementById('password').value;


    let response = await fetch(`${apiUrl}/account/${principal.id}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            'username': `${newUsername}`,
            'password': `${newPassword}`,
            'name': `${newName}`
        })
    });

    if(response.status = 200){
        let data = await response.json();
        sessionStorage.setItem('principal', JSON.stringify(data));
        window.location.href="../html/account.html";
    } else{
        console.log('Unable to login.')
    }
}