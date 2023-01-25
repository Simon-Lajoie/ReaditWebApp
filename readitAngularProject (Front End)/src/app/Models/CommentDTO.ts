import { PostDTO } from "./PostDTO";
import { UserDTO } from "./UserDTO";

export class CommentDTO {
    id: number;
    description: string;
    user: UserDTO;
    publicationDate: Date;
    post: PostDTO;
}