import { PostDTO } from "./PostDTO";
import { UserDTO } from "./UserDTO";

export class UpvoteDTO {
    id: number;
    user: UserDTO;
    post: PostDTO;
}