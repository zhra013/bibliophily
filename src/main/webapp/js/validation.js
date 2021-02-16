function validateEmail(){
    var userMail = document.getElementById("userMail").value;
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
                document.getElementById("submitBtn").disabled = true;
            }
            else if(data === "Allow"){
                document.getElementById("submitBtn").disabled = false;
                $("#userMail_error").removeClass("is-invalid");
                document.getElementById("userMail_error").innerHTML = "";
                document.getElementById("userMail_error").style.display = "none";
                $("#userMail_error").addClass("d-none");
            }
        }
    })
}
function validateContact(){
    var userContact = document.getElementById("userContact").value;
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
                }
                else if(data === "Allow"){
                    document.getElementById("submitBtn").disabled = false;
                    $("#userContact_error").removeClass("is-invalid");
                    document.getElementById("userContact_error").innerHTML = "";
                    document.getElementById("userContact_error").style.display = "none";
                    $("#userContact_error").addClass("d-none");
                }
            }
        })
}