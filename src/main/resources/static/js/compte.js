// Fonctions spécifiques aux comptes
$(document).ready(function() {

    // Validation du formulaire compte
    $('#compteForm').on('submit', function(e) {
        const rib = $('#rib').val().trim();
        const solde = $('#solde').val();
        const clientId = $('#clientId').val();

        if (rib.length < 5) {
            e.preventDefault();
            swal("Erreur", "Le RIB doit contenir au moins 5 caractères", "error");
            return false;
        }

        if (solde === '' || isNaN(solde)) {
            e.preventDefault();
            swal("Erreur", "Veuillez entrer un solde valide", "error");
            return false;
        }

        if (!clientId) {
            e.preventDefault();
            swal("Erreur", "Veuillez sélectionner un client", "error");
            return false;
        }

        return true;
    });

    // Confirmation de suppression avec SweetAlert
    window.confirmDeleteCompte = function(rib) {
        swal({
            title: "Confirmation",
            text: `Voulez-vous vraiment supprimer le compte "${rib}" ?`,
            icon: "warning",
            buttons: ["Annuler", "Supprimer"],
            dangerMode: true,
        }).then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url: "/comptes/delete",
                    type: "POST",
                    data: { rib: rib },
                    success: function(response) {
                        $("#row-" + rib).fadeOut(500, function() {
                            $(this).remove();
                        });
                        swal("Succès", "Compte supprimé avec succès !", "success");
                    },
                    error: function(xhr) {
                        swal("Erreur", "Erreur lors de la suppression du compte.", "error");
                    }
                });
            }
        });
    };

    // Formatage du solde en temps réel
    $('#solde').on('blur', function() {
        const value = parseFloat($(this).val());
        if (!isNaN(value)) {
            $(this).val(value.toFixed(2));
        }
    });
});