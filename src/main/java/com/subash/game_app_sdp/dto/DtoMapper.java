package com.subash.game_app_sdp.dto;

import java.util.Date;

import com.subash.game_app_sdp.modal.Game;
import com.subash.game_app_sdp.modal.Member;
import com.subash.game_app_sdp.modal.CollectionEntry;
import com.subash.game_app_sdp.modal.AdminUser;
import com.subash.game_app_sdp.modal.Transaction;

public final class DtoMapper {
    private DtoMapper() {}

    public static GameDto toDto(Game e) {
        GameDto d = new GameDto();
        d.setId(e.getId());
        d.setName(e.getName());
        d.setDescription(e.getDescription());
        d.setPrice(e.getPrice());
        return d;
    }

    public static Game toEntity(GameDto d) {
        Game e = new Game();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setDescription(d.getDescription());
        e.setPrice(d.getPrice());
        return e;
    }

    public static MemberDto toDto(Member e) {
        MemberDto d = new MemberDto();
        d.setId(e.getId());
        d.setName(e.getName());
        d.setBalance(e.getBalance());
        d.setPhone(e.getPhone());
        return d;
    }

    public static Member toEntity(MemberDto d) {
        Member e = new Member();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setBalance(d.getBalance());
        e.setPhone(d.getPhone());
        return e;
    }

    public static CollectionEntryDto toDto(CollectionEntry e) {
        CollectionEntryDto d = new CollectionEntryDto();
        d.setId(e.getId());
        d.setDate(e.getDate());
        d.setAmount(e.getAmount());
        return d;
    }

    public static CollectionEntry toEntity(CollectionEntryDto d) {
        CollectionEntry e = new CollectionEntry();
        e.setId(d.getId());
        e.setDate(d.getDate());
        e.setAmount(d.getAmount());
        return e;
    }

    public static AdminUserDto toDto(AdminUser e) {
        AdminUserDto d = new AdminUserDto();
        d.setId(e.getId());
        d.setUsername(e.getUsername());
        d.setPassword(e.getPassword());
        return d;
    }

    public static AdminUser toEntity(AdminUserDto d) {
        AdminUser e = new AdminUser();
        e.setId(d.getId());
        e.setUsername(d.getUsername());
        e.setPassword(d.getPassword());
        return e;
    }

    public static TransactionDto toDto(Transaction e) {
        TransactionDto d = new TransactionDto();
        d.setId(e.getId());
        d.setMemberId(e.getMemberId());
        d.setGameId(e.getGameId());
        d.setAmount(e.getAmount());
        d.setDateTime(e.getDateTime());
        return d;
    }

    public static Transaction toEntity(TransactionDto d) {
        Transaction e = new Transaction();
        e.setId(d.getId());
        e.setMemberId(d.getMemberId());
        e.setGameId(d.getGameId());
        e.setAmount(d.getAmount());
        e.setDateTime(d.getDateTime());
        return e;
    }
}


