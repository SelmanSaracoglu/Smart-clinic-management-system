document.getElementById("loginForm").addEventListener("submit", async function (e) {
    e.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    const response = await fetch("/api/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password }),
    });

    if (response.ok) {
        const data = await response.json();
        const { token, role } = data;

        localStorage.setItem("token", token);
        localStorage.setItem("role", role);

        // Yönlendirme
        if (role === "ADMIN") {
            window.location.href = "/admin/adminDashboard";
        } else if (role === "DOCTOR") {
            window.location.href = "/doctor/doctorDashboard";
        } else if (role === "PATIENT") {
            window.location.href = "/patient/patientDashboard";
        } else {
            alert("Unknown role. Access denied.");
        }
    } else {
        document.getElementById("loginError").classList.remove("hidden");
    }
});
