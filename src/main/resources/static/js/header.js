document.addEventListener("DOMContentLoaded", () => {
    const role = localStorage.getItem("role");
    const headerContainer =
        document.getElementById("admin-header") ||
        document.getElementById("doctor-header") ||
        document.getElementById("patient-header");

    if (!headerContainer) return;

    let navHTML = `
        <nav class="main-nav">
            <ul>
    `;

    if (role === "ADMIN") {
        navHTML += `
            <li><a href="/admin/adminDashboard">Dashboard</a></li>
            <li><a href="#">Doctors</a></li>
        `;
    } else if (role === "DOCTOR") {
        navHTML += `
            <li><a href="/doctor/doctorDashboard">Appointments</a></li>
            <li><a href="#">Prescriptions</a></li>
        `;
    } else if (role === "PATIENT") {
        navHTML += `
            <li><a href="/patient/patientDashboard">My Doctors</a></li>
            <li><a href="#">Appointments</a></li>
        `;
    }

    navHTML += `
            <li><a href="#" id="logoutBtn">Logout</a></li>
            </ul>
        </nav>
    `;

    headerContainer.innerHTML = navHTML;

    // Logout işlemi
    const logoutBtn = document.getElementById("logoutBtn");
    logoutBtn.addEventListener("click", () => {
        localStorage.clear();
        window.location.href = "/";
    });
});
