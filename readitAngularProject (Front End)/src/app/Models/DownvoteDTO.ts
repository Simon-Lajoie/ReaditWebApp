import { PostDTO } from "./PostDTO";
import { UserDTO } from "./UserDTO";

export class DownvoteDTO {
    id: number;
    user: UserDTO;
    post: PostDTO;
}