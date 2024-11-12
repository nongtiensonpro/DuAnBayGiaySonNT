package sonnt.service.admin;

import sonnt.model.ChatLieu;
import sonnt.repository.SanPham.AllGiayTheThao.ChatLieuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatLieuService {

    private final ChatLieuRepository chatLieuRepository;

    public ChatLieuService(ChatLieuRepository chatLieuRepository) {
        this.chatLieuRepository = chatLieuRepository;
    }

    public List<ChatLieu> getAll() {
        return chatLieuRepository.findAll();
    }

    public ChatLieu getOne(UUID id) {
        return chatLieuRepository.findById(id).orElse(null);
    }

    public void save(ChatLieu chatLieu) {
        this.chatLieuRepository.save(chatLieu);
    }

    public void delete(UUID id) {
        this.chatLieuRepository.deleteById(id);
    }

    public List<ChatLieu> searchByTen(String tenChatLieu) {
        return chatLieuRepository.findByTenChatLieuContainingOrderById(tenChatLieu);
    }

}
