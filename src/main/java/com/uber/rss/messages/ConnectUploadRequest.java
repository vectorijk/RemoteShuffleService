/*
 * Copyright (c) 2020 Uber Technologies, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.uber.rss.messages;

import com.uber.rss.util.ByteBufUtils;
import io.netty.buffer.ByteBuf;

public class ConnectUploadRequest extends BaseMessage {
    private String user;
    private String appId;
    private String appAttempt;

    public ConnectUploadRequest(String user, String appId, String appAttempt) {
        this.user = user;
        this.appId = appId;
        this.appAttempt = appAttempt;
    }

    @Override
    public int getMessageType() {
        return MessageConstants.MESSAGE_ConnectUploadRequest;
    }

    @Override
    public void serialize(ByteBuf buf) {
        ByteBufUtils.writeLengthAndString(buf, user);
        ByteBufUtils.writeLengthAndString(buf, appId);
        ByteBufUtils.writeLengthAndString(buf, appAttempt);
    }

    public static ConnectUploadRequest deserialize(ByteBuf buf) {
        String user = ByteBufUtils.readLengthAndString(buf);
        String appId = ByteBufUtils.readLengthAndString(buf);
        String appAttempt = ByteBufUtils.readLengthAndString(buf);
        return new ConnectUploadRequest(user, appId, appAttempt);
    }

    public String getUser() {
        return user;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppAttempt() {
        return appAttempt;
    }

    @Override
    public String toString() {
        return "ConnectUploadRequest{" +
                "user='" + user + '\'' +
                ", appId='" + appId + '\'' +
                ", appAttempt='" + appAttempt + '\'' +
                '}';
    }
}
