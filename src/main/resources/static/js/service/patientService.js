const API_BASE = "/api/prescriptions";

function getAuthHeaders() {
    const token = localStorage.getItem("token");
    return {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
    };
}

// 🔹 Hastaya ait reçeteleri getir
export async function getPrescriptionsByPatientName(patientName) {
    try {
        const response = await fetch(`${API_BASE}/by-patient/${encodeURIComponent(patientName)}`, {
            method: "GET",
            headers: getAuthHeaders()
        });

        if (!response.ok) {
            throw new Error("Failed to fetch prescriptions");
        }

        return await response.json();
    } catch (error) {
        console.error("PatientService → getPrescriptionsByPatientName:", error);
        return [];
    }
}
