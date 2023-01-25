import { CommentDTO } from "./CommentDTO";
import { UserDTO } from "./UserDTO";

export class PostDTO {
    id: number;
    description: string;
    publicationDate: Date;
    title: string;
    link: string;
    user: UserDTO;
    number_upvotes: number;
    comments: [CommentDTO];
}