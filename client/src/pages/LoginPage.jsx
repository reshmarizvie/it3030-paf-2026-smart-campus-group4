export const LoginPage = () => {
    const handleLogin = () => {
        window.location.href = 'http://localhost:8080/oauth2/authorization/google';
    };
    return (
        <div style={{ textAlign: 'center', marginTop: '50px' }}>
            <h1>Smart Campus</h1>
            <button onClick={handleLogin}>Login with Google</button>
        </div>
    );
};