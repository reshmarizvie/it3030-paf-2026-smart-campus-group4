export const getToken = () => localStorage.getItem('token');

export const logout = () => {
    localStorage.removeItem('token');
    window.location.href = '/login';
};

export const getUser = () => {
    const token = getToken();
    if (!token) return null;
    try {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        return JSON.parse(window.atob(base64));
    } catch (e) {
        return null;
    }
};

export const isLoggedIn = () => !!getToken();