SERVER_URL = 'http://localhost:8080/';
let token = window.localStorage.getItem('auth_token');

if (token) {
    $('#signin-button').hide();
    $('#signup-button').hide();
    $('#loginForView').hide();
} else {
    $('#dropdownProfile').hide();
    $('#buyButton').attr("disabled", "disabled");
    $('#feedbackTextArea').hide();
    $('#leaveFeedback').hide();
}

if (token) {
    $.ajaxSetup({
        headers: {
            'Authorization': 'Bearer ' + token
        }
    })
}

$('#signin-button').submit(function (e) {
    e.preventDefault();
    window.location.href = 'signin.html';
});

$('#signup-button').submit(function (e) {
    e.preventDefault();
    window.location.href = 'signup.html';
});

$('#logout').click(function (e) {
    window.localStorage.removeItem('auth_token');
    window.location.href = "home.html";
})


function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64));
};

$(window).resize(function () {

    if (document.documentElement.clientWidth < 971) {

        document.getElementById("dropdownProfile").classList.remove('dropleft');
        document.getElementById("dropdownProfile").classList.remove('show');
    }

    if (document.documentElement.clientWidth > 992) {

        document.getElementById("dropdownProfile").classList.add('dropleft');
    }
});

if (document.documentElement.clientWidth < 992) {

    document.getElementById("dropdownProfile").classList.remove('dropleft');
    document.getElementById("dropdownProfile").classList.remove('show');

}


var usernameGlobal = parseJwt(token).sub;

//function getUser1() {
//    $.ajax({
//        url: SERVER_URL + 'users/userid/' + usernameGlobal,
//        method: 'GET',
//        dataType: 'json',
//        contentType: 'application/json',
//        success: function (response) {
//
//            userID = response.id;
//        }
//    });
//}
var defaultImg = 'https://boostchiropractic.co.nz/wp-content/uploads/2016/09/default-user-img.jpg';

function getUser(userID) {
    let imgUrl = SERVER_URL + 'users/image?fileName=';

    $.ajax({
        url: SERVER_URL + 'users/' + userID,
        method: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success: function (response) {

            $('#userImage').append(
                `
                            <img src="${response.image !== null ? (imgUrl + response.image) : defaultImg }" class = "img-fluid rounded-circle" width="200px" height="200px">
                            `
            );
            userId = response.id;

            $('#userInfo').append(
                `
                    <br>
                    <h6>Username : ${response.username}</h6>
                    <h6 class = "fontEmail">Email : ${response.email}</h6>
                    <h6>Age : ${response.age}</h6>
                        `
            );

            $('title').append(
                `
                        ${response.username}   
                        `
            );

        }
    });
}

let role = parseJwt(token).auth;

if (role === 'ROLE_USER') {
    $('#adminPanel').hide();
    $('#all-content').hide();
}
