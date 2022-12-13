export interface User {
  login: string;
  password: string;
  isEmployee: boolean;
}

export interface AuthorizationRequest extends User {
  email: string;
  phone: string;
}
