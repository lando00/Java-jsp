<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <title>Gestion des Employés</title>
        <script>
            function showTable(tableId) {
                document.getElementById('employeeTable').style.display = 'none';
                document.getElementById('locationTable').style.display = 'none';
                document.getElementById('assignmentTable').style.display = 'none';
                document.getElementById(tableId).style.display = 'block';
                searchTables(); // Trigger search after table change
            }

            function openEditEmployeeModal(id, code_emp, nom, prenom, poste) {
                document.getElementById('editEmployeeId').value = id;
                document.getElementById('editCodeEmp').value = code_emp;
                document.getElementById('editNom').value = nom;
                document.getElementById('editPrenom').value = prenom;
                document.getElementById('editPoste').value = poste;
                var modal = new bootstrap.Modal(document.getElementById('editEmployeeModal'));
                modal.show();
            }

            function confirmDeleteEmployee(id) {
                document.getElementById('deleteEmployeeId').value = id;
                var modal = new bootstrap.Modal(document.getElementById('deleteEmployeeModal'));
                modal.show();
            }

            function openEditLocationModal(id, codeLieu, designation, province) {
                document.getElementById('editLocationId').value = id;
                document.getElementById('editCodeLieu').value = codeLieu;
                document.getElementById('editDesignation').value = designation;
                document.getElementById('editProvince').value = province;
                var modal = new bootstrap.Modal(document.getElementById('editLocationModal'));
                modal.show();
            }

            function confirmDeleteLocation(id) {
                document.getElementById('deleteLocationId').value = id;
                var modal = new bootstrap.Modal(document.getElementById('deleteLocationModal'));
                modal.show();
            }

            function openEditAssignmentModal(id, code_emp, codeLieu, dateAffectation) {
                document.getElementById('editAssignmentId').value = id;
                document.getElementById('editCodeEmpAssignment').value = code_emp;
                document.getElementById('editCodeLieuAssignment').value = codeLieu;
                document.getElementById('editDateAffectation').value = dateAffectation;
                var modal = new bootstrap.Modal(document.getElementById('editAssignmentModal'));
                modal.show();
            }

            function confirmDeleteAssignment(id) {
                document.getElementById('deleteAssignmentId').value = id;
                var modal = new bootstrap.Modal(document.getElementById('deleteAssignmentModal'));
                modal.show();
            }

            // Generic search function for all tables
function searchTables() {
    var searchInput = document.getElementById('searchInput').value.toLowerCase();

    // Search in the employees table (by code or name)
    var employeeTable = document.getElementById('employeeTable');
    if (employeeTable.style.display === 'block') {
        var rows = employeeTable.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            var code_emp = row.cells[0].textContent.toLowerCase();
            var nom = row.cells[1].textContent.toLowerCase();
            if (code_emp.includes(searchInput) || nom.includes(searchInput)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        }
    }

    // Search in the locations table (by code or designation)
    var locationTable = document.getElementById('locationTable');
    if (locationTable.style.display === 'block') {
        var rows = locationTable.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            var codeLieu = row.cells[0].textContent.toLowerCase();
            var designation = row.cells[1].textContent.toLowerCase();
            if (codeLieu.includes(searchInput) || designation.includes(searchInput)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        }
    }

    // Search in the assignments table (by code_emp or codeLieu)
    var assignmentTable = document.getElementById('assignmentTable');
    if (assignmentTable.style.display === 'block') {
        var rows = assignmentTable.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            var code_emp = row.cells[0].textContent.toLowerCase();
            var codeLieu = row.cells[1].textContent.toLowerCase();
            if (code_emp.includes(searchInput) || codeLieu.includes(searchInput)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        }
    }
}

            document.addEventListener("DOMContentLoaded", function () {
    // Récupération des variables de session
    const showEmployeeTable = "${sessionScope.showEmployeeTable}";
    const showLocationTable = "${sessionScope.showLocationTable}";
    const showAssignmentTable = "${sessionScope.showAssignmentTable}"; // Ajout pour le tableau des affectations

    // Sélection des tableaux
    const employeeTable = document.getElementById('employeeTable');
    const locationTable = document.getElementById('locationTable');
    const assignmentTable = document.getElementById('assignmentTable');

    // Masquer tous les tableaux par défaut
    employeeTable.style.display = 'none';
    locationTable.style.display = 'none';
    assignmentTable.style.display = 'none';

    // Afficher le tableau approprié selon les variables de session
    if (showEmployeeTable === "true") {
        employeeTable.style.display = 'block';
    } else if (showLocationTable === "true") {
        locationTable.style.display = 'block';
    } else if (showAssignmentTable === "true") {
        assignmentTable.style.display = 'block';
    } else {
        // Par défaut, afficher le tableau des employés
        employeeTable.style.display = 'block';
    }

    // Appliquer la recherche initiale
    searchTables();
});
        </script>
        <style>
        /* Importation de la police pour une touche premium */
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&display=swap');

/* Reset et styles de base */
body {
    font-family: 'Poppins', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
    background: linear-gradient(135deg, #e6e9f0 0%, #eef1f5 100%);
    color: #1a202c;
    line-height: 1.7;
    margin: 0;
    overflow-x: hidden;
}

/* Animations globales */
@keyframes fadeIn {
    from { opacity: 0; transform: translateY(10px); }
    to { opacity: 1; transform: translateY(0); }
}

@keyframes pulse {
    0% { transform: scale(1); }
    50% { transform: scale(1.05); }
    100% { transform: scale(1); }
}

/* Navbar */
.navbar {
    background: rgba(255, 255, 255, 0.95);
    backdrop-filter: blur(12px);
    box-shadow: 0 4px 20px rgba(0, 0, 0, 0.08);
    padding: 1rem 1.5rem;
    position: sticky;
    top: 0;
    z-index: 1000;
    animation: fadeIn 0.5s ease;
}

.navbar-brand {
    font-size: 1.5rem;
    font-weight: 700;
    color: #2b6cb0;
    background: linear-gradient(45deg, #2b6cb0, #63b3ed);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    transition: transform 0.3s ease;
}

.navbar-brand:hover {
    transform: scale(1.05);
}

.nav-link {
    color: #4a5568;
    font-weight: 500;
    padding: 0.75rem 1.25rem;
    border-radius: 10px;
    position: relative;
    transition: color 0.3s ease;
}

.nav-link:hover,
.nav-link.active {
    color: #3182ce;
    background: transparent;
}

.nav-link::after {
    content: '';
    position: absolute;
    bottom: 0;
    left: 50%;
    width: 0;
    height: 3px;
    background: #63b3ed;
    transition: width 0.3s ease, left 0.3s ease;
}

.nav-link:hover::after,
.nav-link.active::after {
    width: 100%;
    left: 0;
}

/* Barre de recherche */
.navbar .form-control {
    border: none;
    border-radius: 12px;
    background: #f1f5f9;
    padding: 0.75rem 1.5rem;
    font-size: 0.95rem;
    transition: all 0.3s ease;
}

.navbar .form-control:focus {
    background: #ffffff;
    box-shadow: 0 0 15px rgba(49, 130, 206, 0.2);
    border-color: #63b3ed;
}

/* Input-group pour l'icône de recherche */
.navbar .input-group {
    max-width: 250px; /* Limite la largeur pour éviter un étirement */
}

.navbar .input-group-text {
    background: #f1f5f9;
    border: none;
    border-radius: 12px 0 0 12px;
    color: #718096;
    padding: 0.75rem 1rem;
}

.navbar .input-group .form-control {
    border-radius: 0 12px 12px 0;
    padding: 0.75rem 1.5rem; /* Réinitialise le padding pour cohérence */
}

/* Menu déroulant */
.dropdown-menu {
    border: none;
    border-radius: 12px;
    background: #ffffff;
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
    padding: 0.75rem;
    animation: fadeIn 0.3s ease;
}

.dropdown-item {
    padding: 0.5rem 1.25rem;
    border-radius: 8px;
    color: #2d3748;
    font-weight: 500;
    transition: all 0.2s ease;
}

.dropdown-item:hover {
    background: linear-gradient(45deg, #e6e9f0, #edf2f7);
    color: #3182ce;
}

/* Conteneur principal */
.container {
    max-width: 1440px;
    margin: 3rem auto;
    padding: 0 1.5rem;
}

/* Carte pour les tables */
.card {
    background: #ffffff;
    border-radius: 16px;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
    padding: 2rem;
    margin-bottom: 2rem;
    position: relative;
    overflow: hidden;
    animation: fadeIn 0.6s ease;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 15px 40px rgba(0, 0, 0, 0.15);
}

.card::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 4px;
    background: linear-gradient(90deg, #3182ce, #63b3ed);
}

/* Titres des tableaux */
.card h2 {
    font-size: 1.75rem;
    font-weight: 700;
    color: #2d3748;
    margin-bottom: 1.5rem;
}

/* Tables */
.table {
    background: #ffffff;
    border-radius: 12px;
    overflow: hidden;
    border-collapse: separate;
    border-spacing: 0;
    box-shadow: inset 0 0 10px rgba(0, 0, 0, 0.03);
    margin-top: 2rem;
}

.table th {
    color: #2d3748;
    font-weight: 600;
    font-size: 0.9rem;
    text-transform: uppercase;
    letter-spacing: 0.05em;
    padding: 1.25rem;
    border-bottom: 2px solid #e2e8f0;
    position: sticky;
    top: 0;
    z-index: 10;
    background: #ffffff;
}

.table td {
    padding: 1.25rem;
    color: #4a5568;
    font-weight: 500;
    border-bottom: 1px solid #f1f5f9;
    vertical-align: middle;
}

.table thead {
    background: transparent;
}

.table thead th {
    background: transparent;
    border-bottom: 2px solid #e2e8f0;
    font-weight: 700;
    font-size: 0.95rem;
}

.table tbody tr {
    transition: background 0.2s ease;
}

.table tbody tr:last-child td {
    border-bottom: none;
}

.table tbody tr:hover {
    background: #f1f5f9;
}

.table td button {
    margin: 0 0.25rem;
}

/* Centrage des messages dans les tableaux vides avec icône */
.table tbody tr td[colspan] {
    text-align: center;
    vertical-align: middle;
    padding: 2rem;
    font-weight: 500;
    color: #718096;
    position: relative;
}

.table tbody tr td[colspan]::before {
    content: '\f05a'; /* Code Unicode pour l'icône fa-info-circle */
    font-family: 'Font Awesome 6 Free';
    font-weight: 900;
    color: #718096;
    margin-right: 0.75rem;
    font-size: 1.2rem;
    vertical-align: middle;
}

/* Boutons */
.btn {
    border-radius: 12px;
    font-weight: 600;
    padding: 0.75rem 1.5rem;
    position: relative;
    overflow: hidden;
    transition: all 0.3s ease;
    display: inline-flex;
    align-items: center;
    justify-content: center;
}

.btn .fas,
.btn .far {
    margin-right: 0.5rem;
    margin-left: 0.5rem;
}

.btn-success {
    background: linear-gradient(45deg, #38a169, #68d391);
    border: none;
}

.btn-success:hover {
    background: linear-gradient(45deg, #2f855a, #4ade80);
    box-shadow: 0 4px 12px rgba(56, 161, 105, 0.4);
    transform: translateY(-2px);
}

.btn-primary {
    background: linear-gradient(45deg, #3182ce, #63b3ed);
    border: none;
}

.btn-primary:hover {
    background: linear-gradient(45deg, #2b6cb0, #4299e1);
    box-shadow: 0 4px 12px rgba(49, 130, 206, 0.4);
    transform: translateY(-2px);
}

.btn-danger {
    background: linear-gradient(45deg, #e53e3e, #f56565);
    border: none;
}

.btn-danger:hover {
    background: linear-gradient(45deg, #c53030, #e53e3e);
    box-shadow: 0 4px 12px rgba(229, 62, 62, 0.4);
    transform: translateY(-2px);
}

.btn-secondary {
    background: linear-gradient(45deg, #718096, #a0aec0);
    border: none;
}

.btn-secondary:hover {
    background: linear-gradient(45deg, #5a667b, #718096);
    box-shadow: 0 4px 12px rgba(113, 128, 150, 0.4);
    transform: translateY(-2px);
}

.btn:active {
    animation: pulse 0.2s;
}

/* Modals */
.modal-content {
    border-radius: 16px;
    border: none;
    background: #ffffff;
    box-shadow: 0 15px 50px rgba(0, 0, 0, 0.2);
    animation: fadeIn 0.4s ease;
}

.modal-header {
    background: linear-gradient(45deg, #f7fafc, #edf2f7);
    border-bottom: none;
    padding: 1.5rem 2rem;
    border-top-left-radius: 16px;
    border-top-right-radius: 16px;
}

.modal-title {
    font-weight: 700;
    color: #2d3748;
    font-size: 1.25rem;
}

.modal-body {
    padding: 2rem;
}

.modal-footer {
    border-top: none;
    padding: 1rem 2rem;
}

/* Formulaires */
.form-control,
.form-select {
    border: none;
    border-radius: 12px;
    background: #f1f5f9;
    padding: 0.85rem 1.5rem;
    font-weight: 500;
    transition: all 0.3s ease;
}

.form-control:focus,
.form-select:focus {
    background: #ffffff;
    box-shadow: 0 0 15px rgba(49, 130, 206, 0.2);
    border-color: #63b3ed;
}

.form-label {
    font-weight: 600;
    color: #2d3748;
    margin-bottom: 0.75rem;
    font-size: 0.95rem;
}

/* Alertes */
.alert {
    border-radius: 12px;
    padding: 1.25rem 1.75rem;
    margin-bottom: 2rem;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    animation: fadeIn 0.5s ease;
}

.alert-success {
    background: linear-gradient(45deg, #f0fff4, #c6f6d5);
    color: #2f855a;
}

.alert-danger {
    background: linear-gradient(45deg, #fff5f5, #fed7d7);
    color: #c53030;
}

/* Icônes */
.fas,
.far {
    transition: transform 0.3s ease;
}

.btn:hover .fas,
.btn:hover .far {
    transform: scale(1.2);
}

/* Responsive */
@media (max-width: 768px) {
    .navbar {
        padding: 0.75rem 1rem;
    }

    .nav-link {
        padding: 0.5rem 1rem;
    }

    .table {
        margin-top: 3rem;
    }

    .table th,
    .table td {
        font-size: 0.85rem;
        padding: 0.75rem;
    }

    .table td[colspan] {
        padding: 1.5rem;
        font-size: 0.9rem;
    }

    .table td[colspan]::before {
        font-size: 1rem;
    }

    .card {
        padding: 1.5rem;
    }

    .modal-content {
        margin: 1rem;
    }

    .btn {
        padding: 0.5rem 1rem;
    }

    .navbar .input-group {
        max-width: 200px;
    }

    .navbar .input-group-text {
        padding: 0.5rem 0.75rem;
        font-size: 0.9rem;
    }

    .navbar .input-group .form-control {
        padding: 0.5rem 1rem;
        font-size: 0.9rem;
    }
}

@media (max-width: 576px) {
    .navbar-brand {
        font-size: 1.25rem;
    }

    .container {
        margin: 1.5rem auto;
        padding: 0 1rem;
    }

    .card {
        padding: 1rem;
    }

    .card h2 {
        font-size: 1.5rem;
        margin-bottom: 1rem;
    }

    .navbar .input-group {
        max-width: 150px;
    }
    
}
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <a class="navbar-brand" href="Page.jsp">Gestion des Employés</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/EmployerController" onclick="showTable('employeeTable')">Employés</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/LieuController" onclick="showTable('locationTable')">Lieux</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/AffecterController" onclick="showTable('assignmentTable')">Affectations</a>
                        </li>
                    </ul>
                    <!-- Search form with a single field -->
                    <form class="d-flex me-3">
                        <div class="input-group">
                        <span class="input-group-text" id="search-icon">
            <i class="fas fa-magnifying-glass"></i>
                        </span>
                        <input class="form-control me-2" type="text" id="searchInput" placeholder="Rechercher" onkeyup="searchTables()" aria-label="Rechercher">
                        </div>
                    </form>
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <i class="fas fa-user-cog"></i>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                                <li>
                                    <a class="dropdown-item" href="deconnexion.jsp">
                                        <i class="fas fa-sign-out-alt"></i> Déconnexion
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="parametres.jsp">
                                        <i class="fas fa-cog"></i> Paramètres
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <c:if test="${not empty sessionScope.message}">
                <div class="alert alert-${sessionScope.messageType} alert-dismissible fade show" role="alert">
                    ${sessionScope.message}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    <% session.removeAttribute("message"); session.removeAttribute("messageType"); %>
                </div>
            </c:if>

            <!-- Employees Table -->
            <div id="employeeTable" style="display: none;">
                <div class="d-flex justify-content-between align-items-center">
                    <h2>Liste des Employés</h2>
                    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addEmployeeModal">
                        <i class="fas fa-plus"></i>
                    </button>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Code Employé</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Poste</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="emp" items="${sessionScope.list_employees}">
                            <tr>
                                <td>${emp.code_emp}</td>
                                <td>${emp.nom}</td>
                                <td>${emp.prenom}</td>
                                <td>${emp.poste}</td>
                                <td>
                                    <button class="btn btn-primary" onclick="openEditEmployeeModal('${emp.id}', '${emp.code_emp}', '${emp.nom}', '${emp.prenom}', '${emp.poste}')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-danger" onclick="confirmDeleteEmployee('${emp.id}')">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty sessionScope.list_employees}">
                            <tr>
                                <td colspan="5">Aucun employé trouvé</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>

            <!-- Locations Table -->
            <div id="locationTable" style="display: none;">
                <div class="d-flex justify-content-between align-items-center">
                    <h2>Liste des Lieux</h2>
                    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addLocationModal">
                        <i class="fas fa-plus"></i>
                    </button>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Code Lieu</th>
                            <th>Désignation</th>
                            <th>Province</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="lieu" items="${sessionScope.listLieu}">
                            <tr>
                                <td>${lieu.codeLieu}</td>
                                <td>${lieu.designation}</td>
                                <td>${lieu.province}</td>
                                <td>
                                    <button class="btn btn-primary" onclick="openEditLocationModal('${lieu.id}', '${lieu.codeLieu}', '${lieu.designation}', '${lieu.province}')">
                                        <i class="fas fa-edit"></i>
                                    </button>
                                    <button class="btn btn-danger" onclick="confirmDeleteLocation('${lieu.id}')">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty sessionScope.listLieu}">
                            <tr>
                                <td colspan="4">Aucun lieu trouvé</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>

            <!-- Assignments Table -->
            <div id="assignmentTable" style="display: none;">
                <div class="d-flex justify-content-between align-items-center">
                    <h2>Liste des Affectations</h2>
                    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addAssignmentModal">
                        <i class="fas fa-plus"></i>
                    </button>
                </div>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Code Employé</th>
                            <th>Code Lieu</th>
                            <th>Date Affectation</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                       
                            <c:forEach var="affect" items="${sessionScope.listaffecter}">
                                <tr>
                                    <td>${affect.code_emp}</td>
                                    <td>${affect.codeLieu}</td>
                                    <td>${affect.dateAffectation}</td>
                                    <td>
                                        <button class="btn btn-primary" onclick="openEditAssignmentModal('${affect.id}', '${affect.code_emp}', '${affect.codeLieu}', '${affect.dateAffectation}')">
                                            <i class="fas fa-edit"></i>
                                        </button>
                                        <button class="btn btn-danger" onclick="confirmDeleteAssignment('${affect.id}')">
                                            <i class="fas fa-trash"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        <c:if test="${empty sessionScope.listaffecter}">
                            <tr>
                                <td colspan="4">Aucune affectation trouvée</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>

            <!-- Modal for Adding Employee -->
            <div class="modal fade" id="addEmployeeModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Ajouter un Employé</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/EmployerController" method="post">
                                <input type="text" name="code_emp" class="form-control mb-3" placeholder="Code Employé" required>
                                <input type="text" name="nom" class="form-control mb-3" placeholder="Nom" required>
                                <input type="text" name="prenom" class="form-control mb-3" placeholder="Prénom" required>
                                <input type="text" name="poste" class="form-control mb-3" placeholder="Poste" required>
                                <button type="submit" class="btn btn-success">Ajouter</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal for Editing Employee -->
            <div class="modal fade" id="editEmployeeModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Modifier un Employé</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/ModifierEmployeur" method="post">
                                <input type="hidden" name="id" id="editEmployeeId">
                                <input type="text" name="code_emp" id="editCodeEmp" class="form-control mb-3" required>
                                <input type="text" name="nom" id="editNom" class="form-control mb-3" required>
                                <input type="text" name="prenom" id="editPrenom" class="form-control mb-3" required>
                                <input type="text" name="poste" id="editPoste" class="form-control mb-3" required>
                                <button type="submit" class="btn btn-primary">Modifier</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal for Deleting Employee -->
            <div class="modal fade" id="deleteEmployeeModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Supprimer un Employé</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <p>Voulez-vous vraiment supprimer cet employé ?</p>
                            <form action="${pageContext.request.contextPath}/SuppressionEmp" method="post">
                                <input type="hidden" name="id" id="deleteEmployeeId">
                                <button type="submit" class="btn btn-danger">Supprimer</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal for Adding Location -->
            <div class="modal fade" id="addLocationModal" tabindex="-1" aria-labelledby="addLocationModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addLocationModalLabel">Ajouter un Lieu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/LieuControlller" method="post">
                                <div class="mb-3">
                                    <label for="codeLieu" class="form-label">Code Lieu</label>
                                    <input type="text" id="codeLieu" name="codeLieu" class="form-control" required>
                                </div>
                                <div class="mb-3">
                                    <label for="designation" class="form-label">Désignation</label>
                                    <input type="text" id="designation" name="designation" class="form-control" required>
                                </div>
                                <div class="mb-3">
                                    <label for="province" class="form-label">Province</label>
                                    <input type="text" id="province" name="province" class="form-control" required>
                                </div>
                                <button type="submit" class="btn btn-success">Ajouter</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal for Editing Location -->
            <div class="modal fade" id="editLocationModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Modifier un Lieu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/UpdateLieu" method="post">
                                <input type="hidden" name="id" id="editLocationId">
                                <div class="mb-3">
                                    <label for="editCodeLieu" class="form-label">Code Lieu</label>
                                    <input type="text" id="editCodeLieu" name="codeLieu" class="form-control" required>
                                </div>
                                <div class="mb-3">
                                    <label for="editDesignation" class="form-label">Désignation</label>
                                    <input type="text" id="editDesignation" name="designation" class="form-control" required>
                                </div>
                                <div class="mb-3">
                                    <label for="editProvince" class="form-label">Province</label>
                                    <input type="text" id="editProvince" name="province" class="form-control" required>
                                </div>
                                <button type="submit" class="btn btn-success">Enregistrer</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal for Deleting Location -->
            <div class="modal fade" id="deleteLocationModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Supprimer un Lieu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <p>Êtes-vous sûr de vouloir supprimer ce lieu ?</p>
                            <form action="${pageContext.request.contextPath}/DeleteLieu" method="post">
                                <input type="hidden" name="id" id="deleteLocationId">
                                <button type="submit" class="btn btn-danger">Supprimer</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal for Adding Assignment -->
            <div class="modal fade" id="addAssignmentModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Affecter un Employé à un Lieu</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/AffecterController" method="post">
                                <div class="mb-3">
                                    <label for="code_emp" class="form-label">Code Employé</label>
                                    <select class="form-select" name="code_emp" required>
                                        <option value="">Sélectionner un employé</option>
                                        <c:forEach var="emp" items="${sessionScope.list_employees}">
                                            <option value="${emp.code_emp}">${emp.nom} ${emp.prenom}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="codeLieu" class="form-label">Code Lieu</label>
                                    <select class="form-select" name="codeLieu" required>
                                        <option value="">Sélectionner un lieu</option>
                                        <c:forEach var="lieu" items="${sessionScope.listLieu}">
                                            <option value="${lieu.codeLieu}">${lieu.codeLieu}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="dateAffectation" class="form-label">Date d'Affectation</label>
                                    <input type="date" id="dateAffectation" name="dateAffectation" class="form-control" required>
                                </div>
                                <button type="submit" class="btn btn-success">Enregistrer</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal for Editing Assignment -->
            <div class="modal fade" id="editAssignmentModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Modifier l'Affectation</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/UpdateAffecter" method="post">
                                <input type="hidden" name="id" id="editAssignmentId">
                                <div class="mb-3">
                                    <label for="editCodeEmpAssignment" class="form-label">Code Employé</label>
                                    <select class="form-select" name="code_emp" id="editCodeEmpAssignment" required>
                                        <option value="">Sélectionner un employé</option>
                                        <c:forEach var="emp" items="${sessionScope.list_employees}">
                                            <option value="${emp.code_emp}">${emp.nom} ${emp.prenom}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="editCodeLieuAssignment" class="form-label">Code Lieu</label>
                                    <select class="form-select" name="codeLieu" id="editCodeLieuAssignment" required>
                                        <option value="">Sélectionner un lieu</option>
                                        <c:forEach var="lieu" items="${sessionScope.listLieu}">
                                            <option value="${lieu.codeLieu}">${lieu.codeLieu}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label for="editDateAffectation" class="form-label">Date d'Affectation</label>
                                    <input type="date" id="editDateAffectation" name="dateAffectation" class="form-control" required>
                                </div>
                                <button type="submit" class="btn btn-success">Enregistrer</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal for Deleting Assignment -->
            <div class="modal fade" id="deleteAssignmentModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Supprimer l'Affectation</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <p>Êtes-vous sûr de vouloir supprimer cette affectation ?</p>
                            <form action="${pageContext.request.contextPath}/DeleteAffecter" method="post">
                                <input type="hidden" name="id" id="deleteAssignmentId">
                                <button type="submit" class="btn btn-danger">Supprimer</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        </div>
    </body>
</html>