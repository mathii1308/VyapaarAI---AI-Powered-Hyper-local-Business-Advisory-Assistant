import React from 'react';

export default function TypingIndicator() {
  return (
    <div className="chat-message assistant typing-message">
      <div className="avatar assistant-avatar">🤖</div>
      <div className="message-bubble glass-card">
        <div className="typing-wrapper">
          <span className="typing-dot"></span>
          <span className="typing-dot"></span>
          <span className="typing-dot"></span>
          <span className="typing-text">VyapaarAI is analyzing your business...</span>
        </div>
      </div>
    </div>
  );
}
