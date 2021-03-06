/*
 *     This file is part of Telegram Server
 *     Copyright (C) 2015  Aykut Alparslan KOÇ
 *
 *     Telegram Server is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Telegram Server is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.telegram.tl;

import org.telegram.mtproto.ProtocolBuffer;
import org.telegram.tl.*;

public class UpdateShortMessageL48 extends TLUpdates {

    public static final int ID = 0x914fbf11;

    public int flags;
    public int id;
    public int user_id;
    public String message;
    public int pts;
    public int pts_count;
    public int date;
    public TLMessageFwdHeader fwd_from;
    public int via_bot_id;
    public int reply_to_msg_id;
    public TLVector<TLMessageEntity> entities;

    public UpdateShortMessageL48() {
        this.entities = new TLVector<>();
    }

    public UpdateShortMessageL48(int flags, int id, int user_id, String message, int pts, int pts_count, int date, TLMessageFwdHeader fwd_from, int via_bot_id, int reply_to_msg_id, TLVector<TLMessageEntity> entities) {
        this.flags = flags;
        this.id = id;
        this.user_id = user_id;
        this.message = message;
        this.pts = pts;
        this.pts_count = pts_count;
        this.date = date;
        this.fwd_from = fwd_from;
        this.via_bot_id = via_bot_id;
        this.reply_to_msg_id = reply_to_msg_id;
        this.entities = entities;
    }

    @Override
    public void deserialize(ProtocolBuffer buffer) {
        flags = buffer.readInt();
        id = buffer.readInt();
        user_id = buffer.readInt();
        message = buffer.readString();
        pts = buffer.readInt();
        pts_count = buffer.readInt();
        date = buffer.readInt();
        if ((flags & (1 << 2)) != 0) {
            fwd_from = (TLMessageFwdHeader) buffer.readTLObject(APIContext.getInstance());
        }
        if ((flags & (1 << 11)) != 0) {
            via_bot_id = buffer.readInt();
        }
        if ((flags & (1 << 3)) != 0) {
            reply_to_msg_id = buffer.readInt();
        }
        if ((flags & (1 << 7)) != 0) {
            entities = (TLVector<TLMessageEntity>) buffer.readTLVector(TLMessageEntity.class);
        }
    }

    @Override
    public ProtocolBuffer serialize() {
        ProtocolBuffer buffer = new ProtocolBuffer(32);
        setFlags();
        serializeTo(buffer);
        return buffer;
    }

    public void setFlags() {
        if (fwd_from != null) {
            flags |= (1 << 2);
        }
        if (via_bot_id != 0) {
            flags |= (1 << 11);
        }
        if (reply_to_msg_id != 0) {
            flags |= (1 << 3);
        }
        if (entities != null) {
            flags |= (1 << 7);
        }
    }

    @Override
    public void serializeTo(ProtocolBuffer buff) {
        buff.writeInt(getConstructor());
        buff.writeInt(flags);
        buff.writeInt(id);
        buff.writeInt(user_id);
        buff.writeString(message);
        buff.writeInt(pts);
        buff.writeInt(pts_count);
        buff.writeInt(date);
        if ((flags & (1 << 2)) != 0) {
            buff.writeTLObject(fwd_from);
        }
        if ((flags & (1 << 11)) != 0) {
            buff.writeInt(via_bot_id);
        }
        if ((flags & (1 << 3)) != 0) {
            buff.writeInt(reply_to_msg_id);
        }
        if ((flags & (1 << 7)) != 0) {
            buff.writeTLObject(entities);
        }
    }

    public boolean is_updateShortMessageL48_out() {
        return (flags & (1 << 1)) != 0;
    }

    public boolean set_updateShortMessageL48_out() {
        return (flags |= (1 << 1)) != 0;
    }

    public boolean is_updateShortMessageL48_mentioned() {
        return (flags & (1 << 4)) != 0;
    }

    public boolean set_updateShortMessageL48_mentioned() {
        return (flags |= (1 << 4)) != 0;
    }

    public boolean is_updateShortMessageL48_media_unread() {
        return (flags & (1 << 5)) != 0;
    }

    public boolean set_updateShortMessageL48_media_unread() {
        return (flags |= (1 << 5)) != 0;
    }

    public boolean is_updateShortMessageL48_silent() {
        return (flags & (1 << 13)) != 0;
    }

    public boolean set_updateShortMessageL48_silent() {
        return (flags |= (1 << 13)) != 0;
    }

    public int getConstructor() {
        return ID;
    }
}