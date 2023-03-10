package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;

    public WhatsappRepository()
    {
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }


    public String createUser(String name, String mobile) throws Exception
    {
        if(userMobile.contains(mobile))
        {
            throw new Exception("User already exists");
        }
        else
        {
            User user = new User(name, mobile);
            userMobile.add(mobile);
            return "SUCCESS";
        }
    }

    public Group createGroup(List<User> users)
    {
        if(users.size() == 2)
        {
            Group group = new Group(users.get(1).getName(), 2);
            group.setAdmin(users.get(0).getName());
            groupUserMap.put(group, users);
            groupMessageMap.put(group, new ArrayList<>());
            adminMap.put(group, users.get(0));
            return group;
        }
        else if(users.size() > 2)
        {
            Group group = new Group("Group "+ ++customGroupCount, users.size());
            group.setAdmin(users.get(0).getName());
//            customGroupCount++;
            groupUserMap.put(group, users);
            groupMessageMap.put(group, new ArrayList<>());
            adminMap.put(group, users.get(0));
            return group;
        }
        return null;
    }

    public int createMessage(String content)
    {
        ++messageId;
        Message msg = new Message(messageId, content);
        return messageId;
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception
    {
        if(!groupUserMap.containsKey(group)) throw new Exception("Group does not exist");
        else if(!groupUserMap.get(group).contains(sender)) throw new Exception("You are not allowed to send message");
        else
        {
            groupMessageMap.get(group).add(message);
            senderMap.put(message, sender);
            return groupMessageMap.get(group).size();
        }
    }

    public String changeAdmin(User approver, User user, Group group) throws Exception
    {
        if(!groupUserMap.containsKey(group)) throw new Exception("Group does not exist");
        else if(!adminMap.get(group).getMobile().equals(approver.getMobile())) throw new Exception("Approver does not have rights");
        else if(!groupUserMap.get(group).contains(user)) throw new Exception("User is not a participant" );
        else
        {
            group.setAdmin(user.getName());
            groupUserMap.get(group).remove(groupMessageMap.get(group)); //remove the user from the group
            groupUserMap.get(group).add(0,user);
            return "SUCCESS";
        }

    }

//    public int removeUser(User user)
//    {
//    }
//
//    public String findMessage(Date start, Date end, int k)
//    {
//    }
}
