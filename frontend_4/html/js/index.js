$(document).ready(function () {
    let menuList = document.getElementsByClassName('sidebar-link')
    
    menuList[1].onclick = function(){
        $('.content').load('home.html', function() {
            document.getElementById('card_username').innerHTML = localStorage.getItem('username')
        })
        
    }
    menuList[2].onclick = function(){
        $('.content').load('account.html', function() {
            loadAccountJs()
        })
    }
    menuList[3].onclick = function(){
        $('.content').load('department.html', function() {
            loadDepartmentJS()
        })
    }
})


if (localStorage.getItem('isTheFirstLogin')) {//login already
    localStorage.removeItem('isTheFirstLogin')
    setUpInfoLogedIn()
}else {
    if (localStorage.getItem('rememberme')) {
        setUpInfoLogedIn()
    }else {
        if(localStorage.getItem('username')) {
            setUpInfoLogedIn()
        }else {
            window.location.replace('signin.html')
        }
        
    }
}

function setUpInfoLogedIn() {
    let username = localStorage.getItem('username')
    document.getElementById('nav_username').innerHTML = username
    document.getElementById('sidebar_username').innerHTML = username
}


function handleSignOut() {
    localStorage.removeItem('username')
    localStorage.removeItem('role')
    //localStorage.removeItem('rememberme')
    localStorage.removeItem('isTheFirstLogin')
    localStorage.removeItem('token')

    console.log('user logout')

    document.getElementById('nav_username').innerHTML = ''
    document.getElementById('sidebar_username').innerHTML = ''

    window.location.replace('signin.html')
}