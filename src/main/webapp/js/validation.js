function validateUserName(){
    var userName = document.getElementById("userName").value;
    $.ajax({
        type: 'get',
        url: 'validate',
        data: { userName: userName, action: "validateUserName" },
        success: function (data) {
            if(data === "UserName Already Exist"){
                $("#userName_error").removeClass("d-none");
                document.getElementById("userName_error").innerHTML = data;
                document.getElementById("userName_error").style.color = "red";
                document.getElementById("userName_error").style.display = "block";
                $("#userName_error").addClass("is-invalid");
                document.getElementById("submitBtn").disabled = true;
                document.getElementById("userName_error").style.paddingBottom = "10px";
            }
            else if(data === "Allow"){
                document.getElementById("submitBtn").disabled = false;
                $("#userName_error").removeClass("is-invalid");
                document.getElementById("userName_error").innerHTML = "";
                document.getElementById("userName_error").style.display = "none";
                $("#userName_error").addClass("d-none");
                document.getElementById("userName_error").style.paddingBottom = "0px";
            }
        }
    })
}
function validateEmail(){
    var userMail = document.getElementById("userMail").value;
    var atposition=userMail.indexOf("@");
    var dotposition=userMail.lastIndexOf(".");
    if (atposition<1 || dotposition<atposition+2 || dotposition+2>=userMail.length){
        $("#userMail_error").removeClass("d-none");
        document.getElementById("userMail_error").innerHTML = "Please Enter Valid Email Address. E.G. abc@gmail.com";
        document.getElementById("userMail_error").style.color = "red";
        document.getElementById("userMail_error").style.display = "block";
        $("#userMail_error").addClass("is-invalid");
        document.getElementById("submitBtn").disabled = true;
    }
    else{
        $.ajax({
            type: 'get',
            url: 'validate',
            data: { userMail: userMail, action: "validateEmail" },
            success: function (data) {
                if(data === "User Already Exist"){
                    $("#userMail_error").removeClass("d-none");
                    document.getElementById("userMail_error").innerHTML = data;
                    document.getElementById("userMail_error").style.color = "red";
                    document.getElementById("userMail_error").style.display = "block";
                    $("#userMail_error").addClass("is-invalid");
                    document.getElementById("userMail_error").style.paddingBottom = "10px";
                    document.getElementById("submitBtn").disabled = true;
                }
                else if(data === "Allow"){
                    document.getElementById("submitBtn").disabled = false;
                    $("#userMail_error").removeClass("is-invalid");
                    document.getElementById("userMail_error").innerHTML = "";
                    document.getElementById("userMail_error").style.display = "none";
                    document.getElementById("userMail_error").style.paddingBottom = "0px";
                    $("#userMail_error").addClass("d-none");
                }
            }
        })
    }
}
function validateContact(){
    var userContact = document.getElementById("userContact").value;
    var re = /^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im;

    if(!re.test(userContact)){
        $("#userContact_error").removeClass("d-none");
        document.getElementById("userContact_error").innerHTML = "Enter Valid Contact Number";
        document.getElementById("userContact_error").style.color = "red";
        document.getElementById("userContact_error").style.display = "block";
        $("#userContact_error").addClass("is-invalid");
        document.getElementById("submitBtn").disabled = true;
        document.getElementById("userContact_error").style.paddingBottom = "10px";
    }
    else{
        $.ajax({
            type: 'get',
            url: 'validate',
            data: { userContact: userContact, action: "validateContact" },
            success: function (data) {
                if(data === "Contact Number Already Exist"){
                    $("#userContact_error").removeClass("d-none");
                    document.getElementById("userContact_error").innerHTML = data;
                    document.getElementById("userContact_error").style.color = "red";
                    document.getElementById("userContact_error").style.display = "block";
                    $("#userContact_error").addClass("is-invalid");
                    document.getElementById("submitBtn").disabled = true;
                    document.getElementById("userContact_error").style.paddingBottom = "10px";
                }
                else if(data === "Allow"){
                    document.getElementById("submitBtn").disabled = false;
                    $("#userContact_error").removeClass("is-invalid");
                    document.getElementById("userContact_error").innerHTML = "";
                    document.getElementById("userContact_error").style.display = "none";
                    $("#userContact_error").addClass("d-none");
                    document.getElementById("userContact_error").style.paddingBottom = "0px";
                }
            }
        })
    }
}

function validateForgotPasswordEmail(){
    var userMail = document.getElementById("forgotUserMail").value;
    var atposition=userMail.indexOf("@");
            var dotposition=userMail.lastIndexOf(".");
            if (atposition<1 || dotposition<atposition+2 || dotposition+2>=userMail.length){
              alert("Please enter a valid e-mail address");
              $("#userMail").val("");
              return null;
              }

    $.ajax({
        type: 'get',
        url: 'validate',
        data: { userMail: userMail, action: "validateEmail" },
        success: function (data) {
            if(data === "Allow"){
                $("#userMail_error").removeClass("d-none");
                document.getElementById("userMail_error").innerHTML = "Email not registered";
                document.getElementById("userMail_error").style.color = "red";
                document.getElementById("userMail_error").style.display = "block";
                $("#userMail_error").addClass("is-invalid");
                 $("#userMail").val("");
                document.getElementById("submitBtn").disabled = true;
            }
            else if(data === "User Already Exist"){
                document.getElementById("submitBtn").disabled = false;
                $("#userMail_error").removeClass("is-invalid");
                document.getElementById("userMail_error").innerHTML = "";
                document.getElementById("userMail_error").style.display = "none";
                $("#userMail_error").addClass("d-none");
            }
        }
    })

}
