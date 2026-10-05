import React, { useEffect, useRef } from 'react';
import ChatMessage from './ChatMessage';
import TypingIndicator from './TypingIndicator';
import SuggestedQuestions from './SuggestedQuestions';

export default function ChatWindow({ messages, isLoading, onSelectQuestion }) {
  const scrollRef = useRef(null);

  useEffect(() => {
    if (scrollRef.current) {
      scrollRef.current.scrollTop = scrollRef.current.scrollHeight;
    }
  }, [messages, isLoading]);

  return (
    <div className="chat-window-container" ref={scrollRef}>
      {messages.map((msg, index) => (
        <ChatMessage key={index} message={msg} />
      ))}

      {isLoading && <TypingIndicator />}

      <SuggestedQuestions onSelectQuestion={onSelectQuestion} disabled={isLoading} />
    </div>
  );
}
