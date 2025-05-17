export const clientId = "1408105754-st2v0784belcum3sq3j8j8sveukk50kn.apps.googleusercontent.com";
export const redirectUri = "http://localhost:5173/oauth-callback";
export const scope = "openid email profile";
export const responseType = "code";

// Google O-Auth URL    

export const googleAuthUrl = 
    `https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}` +
    `&redirect_uri=${encodeURIComponent(redirectUri)}` +
    `&response_type=${responseType}` +
    `&scope=${encodeURIComponent(scope)}`;
