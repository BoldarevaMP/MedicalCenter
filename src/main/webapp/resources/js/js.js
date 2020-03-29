function validate()
{
    let firstName = document.getElementById("FirstName").value;
    //let lastName = document.getElementById("LastName").value;
    //let repassword = document.getElementById("RePassword").value;

    if (firstName === "" || firstName.length < 1) {
        swal("Length must be over 1 symbol");
        //document.getElementById("Validate").innerText = " !!!!!!!!!!!!!!!!!!!!!!!!";
        return false;
    }
    // if (lastName.length < 1) {
    //     alert("Please enter the password.");
    //     return false;
    // }
    // if (repassword == null || repassword == "" || repassword != password) {
    //     alert("Please enter the password.");
    //     return false;
    // }


}