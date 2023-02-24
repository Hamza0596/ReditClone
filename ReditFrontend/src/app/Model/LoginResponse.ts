export class LoginResponse {
    authentificationToken!: string;
    refreshToken!: string;
    expiresAt!: Date;
    userName!: string;
}