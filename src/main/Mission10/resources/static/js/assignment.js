const AssignmentAPI = {
    create: (memberId, data) => httpFetch(`/members/${memberId}/assignments`, { method: 'POST', body: JSON.stringify(data) }),
    getAll: () => httpFetch('/assignments', { method: 'GET' }),
    getByMember: (memberId) => httpFetch(`/members/${memberId}/assignments`, { method: 'GET' }),
    getById: (id) => httpFetch(`/assignments/${id}`, { method: 'GET' }),
    search: (keyword) => httpFetch(`/assignments/search?keyword=${encodeURIComponent(keyword)}`, { method: 'GET' }),
    update: (id, data) => httpFetch(`/assignments/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    delete: (id) => httpFetch(`/assignments/${id}`, { method: 'DELETE' })
};

let currentAssignments = [];

function populateAssignmentMemberSelectors(members) {
    const options = members.map(m => `<option value="${m.id}">${m.name} (${m.part})</option>`).join('');
    const createSelect = document.getElementById('assignment-member-select');
    const filterSelect = document.getElementById('assignment-member-filter');
    if (createSelect) createSelect.innerHTML = options;
    if (filterSelect) filterSelect.innerHTML = `<option value="">-- 멤버 선택 --</option>` + options;
}

function renderAssignmentTable(assignments) {
    currentAssignments = assignments;
    const body = document.getElementById('assignment-list-body');
    body.innerHTML = assignments.map(a => `
        <tr>
            <td>${a.id}</td>
            <td>${a.title}</td>
            <td>${a.description}</td>
            <td>${a.memberName}</td>
            <td>
                <button class="btn-edit" onclick="openAssignmentEdit(${a.id})">수정</button>
                <button class="btn-delete" onclick="deleteAssignment(${a.id})">삭제</button>
            </td>
        </tr>
    `).join('');
}

async function loadAllAssignments() {
    const assignments = await AssignmentAPI.getAll();
    renderAssignmentTable(assignments);
}

async function deleteAssignment(id) {
    await AssignmentAPI.delete(id);
    showToast('과제가 삭제되었습니다.', 'success');
    loadAllAssignments();
}

function findAssignmentById(id) {
    return currentAssignments.find(a => a.id === id);
}

function openAssignmentEdit(id) {
    const assignment = findAssignmentById(id);
    const card = document.getElementById('assignment-edit-card');
    const form = document.getElementById('assignment-edit-form');
    if (assignment) {
        form.title.value = assignment.title;
        form.description.value = assignment.description;
    }
    form.dataset.id = id;
    document.getElementById('assignment-edit-id').textContent = `#${id}`;
    card.style.display = 'block';
    card.scrollIntoView({ behavior: 'smooth' });
}

document.getElementById('btn-assignment-edit-cancel').addEventListener('click', () => {
    document.getElementById('assignment-edit-card').style.display = 'none';
});

document.getElementById('assignment-edit-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const form = e.target;
    const id = form.dataset.id;
    await AssignmentAPI.update(id, { title: form.title.value, description: form.description.value });
    showToast('과제가 수정되었습니다.', 'success');
    document.getElementById('assignment-edit-card').style.display = 'none';
    loadAllAssignments();
});

document.getElementById('assignment-create-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const form = e.target;
    const memberId = form.memberId.value;
    await AssignmentAPI.create(memberId, { title: form.title.value, description: form.description.value });
    showToast('과제가 등록되었습니다.', 'success');
    form.reset();
    loadAllAssignments();
});

document.getElementById('btn-assignment-all').addEventListener('click', loadAllAssignments);

document.getElementById('btn-assignment-by-member').addEventListener('click', async () => {
    const memberId = document.getElementById('assignment-member-filter').value;
    if (!memberId) { showToast('멤버를 선택해주세요.'); return; }
    const assignments = await AssignmentAPI.getByMember(memberId);
    renderAssignmentTable(assignments);
});

document.getElementById('btn-assignment-find-by-id').addEventListener('click', async () => {
    const id = document.getElementById('assignment-id-input').value;
    if (!id) { showToast('과제 ID를 입력해주세요.'); return; }
    const assignment = await AssignmentAPI.getById(id);
    renderAssignmentTable([assignment]);
});

document.getElementById('btn-assignment-search').addEventListener('click', async () => {
    const keyword = document.getElementById('assignment-keyword-input').value.trim();
    if (!keyword) { loadAllAssignments(); return; }
    const assignments = await AssignmentAPI.search(keyword);
    renderAssignmentTable(assignments);
});

document.addEventListener('DOMContentLoaded', loadAllAssignments);
