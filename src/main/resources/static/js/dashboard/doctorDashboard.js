import { getAppointmentsForDoctor } from './appointmentService.js';
import { getPrescriptionsByPatientName } from './patientService.js';
import { openModal, closeModal } from './modal.js';

document.addEventListener("DOMContentLoaded", async () => {
    const appointmentList = document.getElementById("appointmentList");
    const searchInput = document.getElementById("searchByPatientName");
    const dateFilter = document.getElementById("filterByDate");

    let appointments = [];

    async function loadAppointments() {
        appointments = await getAppointmentsForDoctor();
        renderAppointments(appointments);
    }

    function renderAppointments(filtered) {
        appointmentList.innerHTML = "";

        filtered.forEach(app => {
            const card = document.createElement("div");
            card.classList.add("appointment-card");

            card.innerHTML = `
                <h3>${app.patientName}</h3>
                <p><strong>Date:</strong> ${new Date(app.appointmentTime).toLocaleString()}</p>
                <p><strong>Status:</strong> ${app.status}</p>
                <button class="viewPrescriptionBtn" data-patient="${app.patientName}">View Prescriptions</button>
            `;

            appointmentList.appendChild(card);
        });

        // Prescription modal açma
        document.querySelectorAll(".viewPrescriptionBtn").forEach(btn => {
            btn.addEventListener("click", async () => {
                const patientName = btn.getAttribute("data-patient");
                const prescriptions = await getPrescriptionsByPatientName(patientName);
                showPrescriptionModal(patientName, prescriptions);
            });
        });
    }

    // Arama ve filtreleme
    function applyFilters() {
        const nameQuery = searchInput.value.toLowerCase();
        const selectedDate = dateFilter.value;

        const filtered = appointments.filter(app => {
            const nameMatch = app.patientName.toLowerCase().includes(nameQuery);
            const dateMatch = !selectedDate || app.appointmentTime.startsWith(selectedDate);
            return nameMatch && dateMatch;
        });

        renderAppointments(filtered);
    }

    searchInput.addEventListener("input", applyFilters);
    dateFilter.addEventListener("change", applyFilters);

    // Prescription modalı doldur
    function showPrescriptionModal(patientName, prescriptions) {
        document.getElementById("modalPatientName").textContent = patientName;
        const list = document.getElementById("modalMedicationsList");
        list.innerHTML = "";

        if (prescriptions.length === 0) {
            list.innerHTML = "<li>No prescriptions found.</li>";
        } else {
            prescriptions.forEach(p => {
                const meds = p.medications.map(med =>
                    `${med.name} (${med.dosage}, ${med.frequency}, ${med.duration})`
                ).join("<br>");

                const li = document.createElement("li");
                li.innerHTML = `
                    <p><strong>Date:</strong> ${p.dateIssued}</p>
                    <p><strong>Doctor:</strong> ${p.doctorName}</p>
                    <p><strong>Notes:</strong> ${p.notes || "-"}</p>
                    <p><strong>Revisit:</strong> ${p.revisitRecommended ? "Yes" : "No"}</p>
                    <p>${meds}</p>
                    <hr>
                `;
                list.appendChild(li);
            });
        }

        openModal("prescriptionModal");
    }

    document.getElementById("closePrescriptionModal").addEventListener("click", () => closeModal("prescriptionModal"));

    await loadAppointments();
});
