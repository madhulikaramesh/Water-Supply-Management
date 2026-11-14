
const API = "http://localhost:8080/api";

const modules = {
    consumers: {
        title: "Consumers",
        endpoint: "/consumers",
        id: "consumerId",
        fields: [
            { name: "consumerName", label: "Consumer Name", type: "text", required: true },
            { name: "address", label: "Address", type: "text", required: true },
            { name: "phone", label: "Phone Number", type: "tel", required: true }
        ],
        columns: [
            ["consumerId", "Consumer ID"],
            ["consumerName", "Consumer Name"],
            ["address", "Address"],
            ["phone", "Phone"]
        ]
    },

    connections: {
        title: "Water Connections",
        endpoint: "/water-connections",
        id: "connectionId",
        fields: [
            { name: "consumerId", label: "Consumer ID", type: "number", required: true },
            {
                name: "connectionType",
                label: "Connection Type",
                type: "select",
                options: ["Domestic", "Commercial", "Industrial"],
                required: true
            },
            { name: "connectionDate", label: "Connection Date", type: "date", required: true },
            {
                name: "status",
                label: "Status",
                type: "select",
                options: ["Active", "Inactive", "Pending"],
                required: true
            }
        ],
        columns: [
            ["connectionId", "Connection ID"],
            ["consumerId", "Consumer ID"],
            ["connectionType", "Connection Type"],
            ["connectionDate", "Connection Date"],
            ["status", "Status"]
        ]
    },

    readings: {
        title: "Meter Readings",
        endpoint: "/meter-readings",
        id: "readingId",
        fields: [
            { name: "connectionId", label: "Connection ID", type: "number", required: true },
            { name: "readingDate", label: "Reading Date", type: "date", required: true },
            { name: "previousReading", label: "Previous Reading", type: "number", step: "0.01", required: true },
            { name: "currentReading", label: "Current Reading", type: "number", step: "0.01", required: true }
        ],
        columns: [
            ["readingId", "Reading ID"],
            ["connectionId", "Connection ID"],
            ["readingDate", "Reading Date"],
            ["previousReading", "Previous Reading"],
            ["currentReading", "Current Reading"]
        ]
    },

    bills: {
        title: "Water Bills",
        endpoint: "/water-bills",
        id: "billId",
        fields: [
            { name: "readingId", label: "Reading ID", type: "number", required: true },
            { name: "unitsConsumed", label: "Units Consumed", type: "number", step: "0.01", required: true },
            { name: "billAmount", label: "Bill Amount (₹)", type: "number", step: "0.01", required: true },
            { name: "dueDate", label: "Due Date", type: "date", required: true },
            {
                name: "paymentStatus",
                label: "Payment Status",
                type: "select",
                options: ["Paid", "Unpaid", "Pending"],
                required: true
            }
        ],
        columns: [
            ["billId", "Bill ID"],
            ["readingId", "Reading ID"],
            ["unitsConsumed", "Units Consumed"],
            ["billAmount", "Bill Amount"],
            ["dueDate", "Due Date"],
            ["paymentStatus", "Payment Status"]
        ]
    },

    payments: {
        title: "Payments",
        endpoint: "/payments",
        id: "paymentId",
        fields: [
            { name: "billId", label: "Bill ID", type: "number", required: true },
            { name: "paymentDate", label: "Payment Date", type: "date", required: true },
            { name: "amountPaid", label: "Amount Paid (₹)", type: "number", step: "0.01", required: true },
            {
                name: "paymentMethod",
                label: "Payment Method",
                type: "select",
                options: ["Cash", "UPI", "Card", "Net Banking"],
                required: true
            }
        ],
        columns: [
            ["paymentId", "Payment ID"],
            ["billId", "Bill ID"],
            ["paymentDate", "Payment Date"],
            ["amountPaid", "Amount Paid"],
            ["paymentMethod", "Payment Method"]
        ],
        editable: true
    }
};

const records = {
    consumers: [],
    connections: [],
    readings: [],
    bills: [],
    payments: []
};

const pageTitles = {
    dashboard: ["Dashboard", "Overview of your water supply system"],
    consumers: ["Consumers", "Manage registered consumers"],
    connections: ["Water Connections", "Manage water connections"],
    readings: ["Meter Readings", "Manage meter readings"],
    bills: ["Water Bills", "Manage water bills"],
    payments: ["Payments", "Manage payment records"],
    about: ["About", "About our water supply management system"]
};

function showToast(message) {
    const toast = document.getElementById("toast");
    toast.textContent = message;
    toast.classList.add("show");

    setTimeout(() => {
        toast.classList.remove("show");
    }, 3000);
}

function escapeHTML(value) {
    return String(value ?? "").replace(/[&<>"']/g, character => ({
        "&": "&amp;",
        "<": "&lt;",
        ">": "&gt;",
        '"': "&quot;",
        "'": "&#039;"
    })[character]);
}

async function apiRequest(url, options = {}) {
    const response = await fetch(url, {
        ...options,
        headers: {
            "Content-Type": "application/json",
            ...(options.headers || {})
        }
    });

    if (!response.ok) {
        const message = await response.text();
        throw new Error(message || `Request failed: ${response.status}`);
    }

    if (response.status === 204) {
        return null;
    }

    const text = await response.text();
    return text ? JSON.parse(text) : null;
}

async function loadModule(moduleName) {
    const module = modules[moduleName];

    try {
        const data = await apiRequest(API + module.endpoint);
        records[moduleName] = Array.isArray(data) ? data : [];
        renderTable(moduleName);
        updateDashboard();
    } catch (error) {
        console.error(error);
        records[moduleName] = [];
        renderTable(moduleName);
        showToast(`Unable to load ${module.title}. Check backend connection.`);
    }
}

async function loadAllModules() {
    const names = Object.keys(modules);
    await Promise.all(names.map(name => loadModule(name)));
}

function showPage(pageName) {
    document.querySelectorAll(".page").forEach(page => {
        page.classList.remove("active-page");
    });

    document.querySelectorAll(".nav-item").forEach(button => {
        button.classList.remove("active");
    });

    const page = document.getElementById(`${pageName}-page`);

    if (!page) return;

    page.classList.add("active-page");

    const navButton = document.querySelector(
        `.nav-item[data-page="${pageName}"]`
    );

    if (navButton) {
        navButton.classList.add("active");
    }

    document.getElementById("page-title").textContent =
        pageTitles[pageName][0];

    document.getElementById("page-subtitle").textContent =
        pageTitles[pageName][1];

    if (modules[pageName]) {
        loadModule(pageName);
    }
}

function renderForm(moduleName) {
    const module = modules[moduleName];
    const form = document.getElementById(`${moduleName}-form`);

    let fieldsHTML = module.fields.map(field => {
        let control = "";

        if (field.type === "select") {
            control = `
                <select name="${field.name}" ${field.required ? "required" : ""}>
                    <option value="">Select ${field.label}</option>
                    ${field.options.map(option => `
                        <option value="${escapeHTML(option)}">${escapeHTML(option)}</option>
                    `).join("")}
                </select>
            `;
        } else {
            control = `
                <input
                    type="${field.type}"
                    name="${field.name}"
                    ${field.step ? `step="${field.step}"` : ""}
                    ${field.type === "number" ? 'min="0"' : ""}
                    ${field.name === "phone" ? 'pattern="[0-9]{10}" minlength="10" maxlength="10" inputmode="numeric" title="Enter exactly 10 digits"' : ""}
                    ${field.required ? "required" : ""}
                    placeholder="Enter ${field.label}"
                >
            `;
        }

        return `
            <div class="form-field">
                <label>${field.label}</label>
                ${control}
            </div>
        `;
    }).join("");

    form.innerHTML = `
        ${fieldsHTML}
        <div class="form-actions">
            <button type="submit" class="primary-button" id="${moduleName}-submit">
                Add ${module.title.replace(/s$/, "")}
            </button>
            <button type="button" class="cancel-button" id="${moduleName}-cancel" hidden>
                Cancel
            </button>
        </div>
    `;

    // Allow only digits in the phone field and limit input to 10 digits.
    if (moduleName === "consumers") {
        const phoneInput = form.elements.namedItem("phone");

        phoneInput.addEventListener("input", () => {
            phoneInput.value = phoneInput.value
                .replace(/[^0-9]/g, "")
                .slice(0, 10);
        });
    }

    form.addEventListener("submit", event => {
        submitForm(event, moduleName);
    });

    document.getElementById(`${moduleName}-cancel`).addEventListener("click", () => {
        resetForm(moduleName);
    });
}

function renderTable(moduleName) {
    const module = modules[moduleName];

    const head = document.getElementById(`${moduleName}-head`);
    const body = document.getElementById(`${moduleName}-body`);
    const count = document.getElementById(`${moduleName}-count`);

    head.innerHTML = `
        <tr>
            ${module.columns.map(column => `<th>${column[1]}</th>`).join("")}
            <th>Actions</th>
        </tr>
    `;

    count.textContent = `${records[moduleName].length} Records`;

    if (records[moduleName].length === 0) {
        body.innerHTML = `
            <tr>
                <td colspan="${module.columns.length + 1}" class="empty-row">
                    No records found. Add your first record above.
                </td>
            </tr>
        `;
        return;
    }

    body.innerHTML = records[moduleName].map(record => {
        const id = record[module.id];

        const cells = module.columns.map(column => {
            const value = record[column[0]] ?? "-";
            return `<td>${escapeHTML(value)}</td>`;
        }).join("");

        const editButton = module.editable
            ? `<button class="action-button edit-button" data-edit="${escapeHTML(id)}">Edit</button>`
            : "";

        return `
            <tr>
                ${cells}
                <td>
                    ${editButton}
                    <button class="action-button delete-button" data-delete="${escapeHTML(id)}">
                        Delete
                    </button>
                </td>
            </tr>
        `;
    }).join("");

    body.querySelectorAll("[data-delete]").forEach(button => {
        button.addEventListener("click", () => {
            deleteRecord(moduleName, button.dataset.delete);
        });
    });

    body.querySelectorAll("[data-edit]").forEach(button => {
        button.addEventListener("click", () => {
            editPayment(button.dataset.edit);
        });
    });
}

async function submitForm(event, moduleName) {
    event.preventDefault();

    const module = modules[moduleName];
    const form = event.target;

    // Check browser form validation.
    if (!form.reportValidity()) {
        return;
    }

    const formData = new FormData(form);
    const data = {};

    for (const field of module.fields) {
        const value = formData.get(field.name);

        if (value === null || value.trim() === "") {
            showToast(`Please enter ${field.label}`);
            return;
        }

        if (field.type === "number") {
            data[field.name] = Number(value);
        } else {
            data[field.name] = value;
        }
    }

    // Validate consumer phone number: exactly 10 digits.
    if (moduleName === "consumers" && !/^[0-9]{10}$/.test(data.phone)) {
        showToast("Phone number must contain exactly 10 digits.");
        return;
    }

    if (moduleName === "readings" &&
        data.currentReading < data.previousReading) {
        showToast("Current reading cannot be less than previous reading.");
        return;
    }

    const editingId = form.dataset.editingId;
    const isEditing = Boolean(editingId);

    if (isEditing && !module.editable) {
        showToast("Update is not supported for this module.");
        return;
    }

    const url = API + module.endpoint +
        (isEditing ? `/${editingId}` : "");

    try {
        await apiRequest(url, {
            method: isEditing ? "PUT" : "POST",
            body: JSON.stringify(data)
        });

        showToast(isEditing ? "Record updated successfully!" : "Record added successfully!");

        resetForm(moduleName);
        await loadModule(moduleName);
    } catch (error) {
        console.error(error);
        showToast("Unable to save record. Check the entered IDs and backend.");
    }
}

async function deleteRecord(moduleName, id) {
    const module = modules[moduleName];

    if (!confirm(`Are you sure you want to delete record ${id}?`)) {
        return;
    }

    try {
        await apiRequest(`${API}${module.endpoint}/${id}`, {
            method: "DELETE"
        });

        showToast("Record deleted successfully!");
        await loadModule(moduleName);
    } catch (error) {
        console.error(error);
        showToast("Unable to delete record. It may be linked to another record.");
    }
}

function editPayment(id) {
    const payment = records.payments.find(
        record => String(record.paymentId) === String(id)
    );

    if (!payment) return;

    const form = document.getElementById("payments-form");

    Object.keys(payment).forEach(key => {
        const input = form.elements.namedItem(key);

        if (input) {
            input.value = payment[key] ?? "";
        }
    });

    form.dataset.editingId = id;

    document.getElementById("payments-submit").textContent = "Update Payment";
    document.getElementById("payments-cancel").hidden = false;

    form.scrollIntoView({ behavior: "smooth", block: "center" });
    showToast(`Editing payment #${id}`);
}

function resetForm(moduleName) {
    const form = document.getElementById(`${moduleName}-form`);

    form.reset();
    delete form.dataset.editingId;

    const module = modules[moduleName];

    document.getElementById(`${moduleName}-submit`).textContent =
        `Add ${module.title.replace(/s$/, "")}`;

    document.getElementById(`${moduleName}-cancel`).hidden = true;
}

function updateDashboard() {
    document.getElementById("total-consumers").textContent =
        records.consumers.length;

    document.getElementById("total-connections").textContent =
        records.connections.length;

    document.getElementById("total-readings").textContent =
        records.readings.length;

    document.getElementById("total-bills").textContent =
        records.bills.length;

    const totalPaid = records.payments.reduce((total, payment) => {
        return total + Number(payment.amountPaid || 0);
    }, 0);

    document.getElementById("total-collected").textContent =
        "₹" + totalPaid.toLocaleString("en-IN", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        });

    const recent = records.consumers.slice(-5).reverse();
    const recentBody = document.getElementById("recent-consumers");

    if (recent.length === 0) {
        recentBody.innerHTML = `
            <tr><td colspan="3" class="empty-row">No consumer records available</td></tr>
        `;
    } else {
        recentBody.innerHTML = recent.map(consumer => `
            <tr>
                <td>${escapeHTML(consumer.consumerId)}</td>
                <td>${escapeHTML(consumer.consumerName)}</td>
                <td>${escapeHTML(consumer.phone)}</td>
            </tr>
        `).join("");
    }
}

// Sidebar navigation
document.querySelectorAll(".nav-item").forEach(button => {
    button.addEventListener("click", () => {
        showPage(button.dataset.page);
    });
});

// Dashboard navigation buttons
document.querySelectorAll("[data-goto]").forEach(button => {
    button.addEventListener("click", () => {
        showPage(button.dataset.goto);
    });
});

// Create all module forms
Object.keys(modules).forEach(moduleName => {
    renderForm(moduleName);
});

// Start application
showPage("dashboard");
loadAllModules();
