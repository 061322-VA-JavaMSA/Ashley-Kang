let welcomeH1 = document.getElementById('welcome');

if(principal){
    welcomeH1.innerHTML = `Welcome back ${principal.user_name}!`
} else{
    welcomeH1.innerHTML = `Welcome to Reimbursement Pro!`
}