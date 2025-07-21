export function createDoctorCard(doctor) {
    const card = document.createElement("div");
    card.classList.add("doctor-card");

    card.innerHTML = `
        <h3>${doctor.first_name} ${doctor.lastname}</h3>
        <p><strong>Specialization:</strong> ${doctor.specialization}</p>
        <p><strong>Email:</strong> ${doctor.email}</p>
        <p><strong>Phone:</strong> ${doctor.phone || "-"}</p>
        <p><strong>Available Times:</strong> ${doctor.availableTimes?.join(", ") || "-"}</p>
    `;

    // Opsiyonel: Admin panelinde buton eklenebilir
    // const deleteBtn = document.createElement("button");
    // deleteBtn.textContent = "Delete";
    // deleteBtn.addEventListener("click", () => deleteDoctor(doctor.doctor_id));
    // card.appendChild(deleteBtn);

    return card;
}
