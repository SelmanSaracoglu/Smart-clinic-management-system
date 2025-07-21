const API_BASE = "/api/doctors";

// Token'ı localStorage'tan al
function getAuthHeaders() {
    const token = localStorage.getItem("token");
    return {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
    };
}

// 🔹 Doktorları getir
export async function getAllDoctors() {
    try {
        const response = await fetch(`${API_BASE}/all`, {
            method: "GET",
            headers: getAuthHeaders()
        });

        if (!response.ok) {
            throw new Error("Failed to fetch doctors");
        }

        return await response.json();
    } catch (error) {
        console.error("DoctorService → getAllDoctors:", error);
        return [];
    }
}

// 🔹 Yeni doktor ekle
export async function addDoctor(doctorData) {
    try {
        const response = await fetch(`${API_BASE}/add`, {
            method: "POST",
            headers: getAuthHeaders(),
            body: JSON.stringify(doctorData)
        });

        if (!response.ok) {
            throw new Error("Failed to add doctor");
        }

        return await response.json();
    } catch (error) {
        console.error("DoctorService → addDoctor:", error);
        alert("Doctor could not be added.");
    }
}
