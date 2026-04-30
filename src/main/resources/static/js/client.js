// Fonctions spécifiques aux clients
$(document).ready(function() {

    // Validation du formulaire client
    $('#clientForm').on('submit', function(e) {
        const nom = $('#nom').val().trim();
        const prenom = $('#prenom').val().trim();

        if (nom.length < 2) {
            e.preventDefault();
            swal("Erreur", "Le nom doit contenir au moins 2 caractères", "error");
            return false;
        }

        if (prenom.length < 2) {
            e.preventDefault();
            swal("Erreur", "Le prénom doit contenir au moins 2 caractères", "error");
            return false;
        }

        return true;
    });

    // Confirmation de suppression avec SweetAlert
    window.confirmDeleteClient = function(id, name) {
        swal({
            title: "Confirmation",
            text: `Voulez-vous vraiment supprimer le client "${name}" ?`,
            icon: "warning",
            buttons: ["Annuler", "Supprimer"],
            dangerMode: true,
        }).then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url: "/clients/delete",
                    type: "POST",
                    data: { id: id },
                    success: function(response) {
                        $("#row-" + id).fadeOut(500, function() {
                            $(this).remove();
                        });
                        swal("Succès", "Client supprimé avec succès !", "success");
                    },
                    error: function(xhr) {
                        swal("Erreur", "Impossible de supprimer ce client. Il possède peut-être des comptes associés.", "error");
                    }
                });
            }
        });
    };
});