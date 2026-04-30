// Configuration globale AJAX
$(document).ready(function() {

    // Toast notifications
    function showToast(message, type = 'success') {
        const toastHTML = `
            <div class="alert alert-${type} alert-dismissible fade show position-fixed top-0 end-0 m-3"
                 role="alert" style="z-index: 9999;">
                ${message}
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        `;
        $('body').append(toastHTML);
        setTimeout(() => {
            $('.alert').alert('close');
        }, 3000);
    }

    // Désactiver le bouton pendant la soumission AJAX
    $(document).ajaxStart(function() {
        $('button[type="submit"]').prop('disabled', true);
    });

    $(document).ajaxStop(function() {
        $('button[type="submit"]').prop('disabled', false);
    });

    // Fermeture automatique des alertes
    setTimeout(function() {
        $('.alert-dismissible').fadeOut('slow');
    }, 4000);
});