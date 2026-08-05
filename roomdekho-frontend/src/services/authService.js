import api from "../api/axiosConfig";

export const loginUser = (data) => api.post("/users/login", data);
export const registerUser = (data) => api.post("/users/register", data);
export const loginAdmin = (data) => api.post("/admin/login", data);