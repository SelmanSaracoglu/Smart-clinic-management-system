const API_BASE = "/api/appointments";

function getAuthHeaders() {
    const token = localStorage.getItem("token");
    return {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
    };
}

// 🔹 Giriş yapan doktorun randevularını getir
export async function getAppointmentsForDoctor() {
    try {
        const response = await fetch(`${API_BASE}/doctor`, {
            method: "GET",
            headers: getAuthHeaders()
        });

        if (!response.ok) {
            throw new Error("Failed to fetch appointments");
        }

        return await response.json();
    } catch (error) {
        console.error("AppointmentService → getAppointmentsForDoctor:", error);
        return [];
    }
}
