/**
 * 
 */
function handleLoginRequest(xhr, status, args) {
        if(args.validationFailed || !args.loggedIn) {
            PF('loginWindow').jq.effect("shake", {times:5}, 100);
        }
        else {
            PF('loginWindow').hide();
            $('#loginLink').fadeOut();
        }
    }