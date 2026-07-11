const MemberAPI = {
    getAll: () => httpFetch('/members', { method: 'GET' }),
    getByPart: (part) => httpFetch(`/members?part=${encodeURIComponent(part)}`, { method: 'GET' }),
    getById: (id) => httpFetch(`/members/${id}`, { method: 'GET' }),
    createLion: (data) => httpFetch('/members/lions', { method: 'POST', body: JSON.stringify(data) }),
    createStaff: (data) => httpFetch('/members/staffs', { method: 'POST', body: JSON.stringify(data) }),
    updateLion: (id, data) => httpFetch(`/members/lions/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    updateStaff: (id, data) => httpFetch(`/members/staffs/${id}`, { method: 'PUT', body: JSON.stringify(data) }),
    delete: (id) => httpFetch(`/members/${id}`, { method: 'DELETE' })
};

let currentMemberRole = 'lion';
let currentMembers = [];

function renderMemberTable(members) {
    currentMembers = members;
    const body = document.getElementById('member-list-body');
    body.innerHTML = members.map(m => `
        <tr>
            <td>${m.id}</td>
            <td>${m.name}</td>
            <td>${m.part}</td>
            <td>${m.generation}</td>
            <td>${m.roleName}</td>
            <td>${m.roleName === '아기사자' ? (m.studentId || '') : (m.position || '')}</td>
            <td>
                <button class="btn-edit" onclick="openMemberEdit(${m.id})">수정</button>
                <button class="btn-delete" onclick="deleteMember(${m.id})">삭제</button>
            </td>
        </tr>
    `).join('');
    populateAssignmentMemberSelectors(members);
}

async function loadAllMembers() {
    const members = await MemberAPI.getAll();
    renderMemberTable(members);
}

async function loadMembersByPart(part) {
    const members = await MemberAPI.getByPart(part);
    renderMemberTable(members);
}

async function deleteMember(id) {
    await MemberAPI.delete(id);
    showToast('멤버가 삭제되었습니다.', 'success');
    loadAllMembers();
}

function findMemberById(id) {
    return currentMembers.find(m => m.id === id);
}

function openMemberEdit(id) {
    const member = findMemberById(id);
    if (!member) return;
    const isLion = member.roleName === '아기사자';
    const card = document.getElementById('member-edit-card');
    const form = document.getElementById('member-edit-form');
    form.dataset.id = id;
    form.dataset.role = isLion ? 'lion' : 'staff';
    form.major.value = member.major;
    form.generation.value = member.generation;
    form.part.value = member.part;
    card.querySelector('.lion-only-edit').style.display = isLion ? 'flex' : 'none';
    card.querySelector('.staff-only-edit').style.display = isLion ? 'none' : 'flex';
    if (isLion) form.studentId.value = member.studentId || '';
    else form.position.value = member.position || '';
    document.getElementById('member-edit-id').textContent = `#${id}`;
    card.style.display = 'block';
    card.scrollIntoView({ behavior: 'smooth' });
}

document.getElementById('btn-member-edit-cancel').addEventListener('click', () => {
    document.getElementById('member-edit-card').style.display = 'none';
});

document.getElementById('member-edit-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const form = e.target;
    const id = form.dataset.id;
    const role = form.dataset.role;
    const payload = {
        major: form.major.value,
        generation: Number(form.generation.value),
        part: form.part.value
    };
    if (role === 'lion') {
        payload.studentId = form.studentId.value;
        await MemberAPI.updateLion(id, payload);
    } else {
        payload.position = form.position.value;
        await MemberAPI.updateStaff(id, payload);
    }
    showToast('멤버 정보가 수정되었습니다.', 'success');
    document.getElementById('member-edit-card').style.display = 'none';
    loadAllMembers();
});

document.getElementById('btn-role-lion').addEventListener('click', () => switchMemberRole('lion'));
document.getElementById('btn-role-staff').addEventListener('click', () => switchMemberRole('staff'));

function switchMemberRole(role) {
    currentMemberRole = role;
    document.getElementById('btn-role-lion').classList.toggle('active', role === 'lion');
    document.getElementById('btn-role-staff').classList.toggle('active', role === 'staff');
    document.querySelector('.lion-only').style.display = role === 'lion' ? 'flex' : 'none';
    document.querySelector('.staff-only').style.display = role === 'staff' ? 'flex' : 'none';
}

document.getElementById('member-create-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const form = e.target;
    const payload = {
        name: form.name.value,
        major: form.major.value,
        generation: Number(form.generation.value),
        part: form.part.value
    };
    if (currentMemberRole === 'lion') {
        payload.studentId = form.studentId.value;
        await MemberAPI.createLion(payload);
    } else {
        payload.position = form.position.value;
        await MemberAPI.createStaff(payload);
    }
    showToast('멤버가 등록되었습니다.', 'success');
    form.reset();
    loadAllMembers();
});

document.getElementById('btn-member-all').addEventListener('click', loadAllMembers);
document.getElementById('btn-member-filter').addEventListener('click', () => {
    const part = document.getElementById('member-part-filter').value.trim();
    if (!part) { loadAllMembers(); return; }
    loadMembersByPart(part);
});

document.addEventListener('DOMContentLoaded', loadAllMembers);
